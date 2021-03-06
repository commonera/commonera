#!/usr/bin/env python

# http://ferry.opencore.io/en/latest/examples/spark.html

import sys
from pyspark import SparkContext
from pyspark.mllib.recommendation import ALS
from numpy import array

if __name__ == "__main__":
    #data_file = '/spark/data/als.data'
    data_file = "hdfs://172.17.0.12:9000/user/hdfs/Posts.xml"

    sc = SparkContext(sys.argv[1], "Collaborative Filtering")
    data = sc.textFile(data_file)
    ratings = data.map(lambda line: array([float(x) for x in line.split(',')]))

    # Build the recommendation model using Alternating Least Squares
    model = ALS.train(ratings, 1, 20)

    # Evaluate the model on training data
    testdata = ratings.map(lambda p: (int(p[0]), int(p[1])))
    predictions = model.predictAll(testdata).map(lambda r: ((r[0], r[1]), r[2]))
    ratesAndPreds = ratings.map(lambda r: ((r[0], r[1]), r[2])).join(predictions)
    MSE = ratesAndPreds.map(lambda r: (r[1][0] - r[1][1])**2).reduce(lambda x, y: x + y)/ratesAndPreds.count()
    print("Mean Squared Error = " + str(MSE))
