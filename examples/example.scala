// scp /datasets/stackexchange/stackexchange/academia.stackexchange.com.7z spark-master:/tmp/
// ssh spark-master
// apt-get update && apt-get install -y p7zip-full
// su - hdfs
// cd /tmp
// 7z x academia.stackexchange.com.7z
// cat Posts.xml | hadoop fs -put - /user/hdfs/Posts.xml

val postsXML = sc.textFile("hdfs://172.17.0.12:9000/user/hdfs/Posts.xml")
postsXML.count()
postsXML.map({line => line}).collect()

val postIDTags = postsXML.flatMap { line =>
  // Matches Id="..." ... Tags="..." in  line
  val idTagRegex = "Id=\"(\\d+)\".+Tags=\"([^\"]+)\"".r

  // // Finds tags like <TAG> value from above
  val tagRegex = "&lt;([^&]+)&gt;".r

  // Yields 0 or 1 matches:
  idTagRegex.findFirstMatchIn(line) match {
    // No match -- not a  line
    case None => None
    // Match, and can extract ID and tags from m
    case Some(m) => {
      val postID = m.group(1).toInt
      val tagsString = m.group(2)
      // Pick out just TAG matching group
      val tags = tagRegex.findAllMatchIn(tagsString).map(_.group(1)).toList
      // Keep only question with at least 4 tags, and map to (post,tag) tuples
      if (tags.size >= 4) tags.map((postID,_)) else None
     }
  }
  // Because of flatMap, individual lists will concatenate
  // into one collection of tuples
}

def nnHash(tag: String) = tag.hashCode & 0x7FFFFF
var tagHashes = postIDTags.map(_._2).distinct.map(tag =>(nnHash(tag),tag))

import org.apache.spark.mllib.recommendation._
// Convert to Rating(Int,Int,Double) objects
val alsInput = postIDTags.map(t => Rating(t._1, nnHash(t._2), 1.0))
// Train model with 40 features, 10 iterations of ALS
val model = ALS.trainImplicit(alsInput, 40, 10)

def recommend(questionID: Int, howMany: Int = 5): Array[(String, Double)] = {
  // Build list of one question and all items and predict value for all of them
  val predictions = model.predict(tagHashes.map(t => (questionID,t._1)))
  // Get top howMany recommendations ordered by prediction value
  val topN = predictions.top(howMany)(Ordering.by[Rating,Double](_.rating))
  // Translate back to tags from IDs
  topN.map(r => (tagHashes.lookup(r.product)(0), r.rating))
}

// How to avoid procrastination during the research phase of my PhD?
// http://academia.stackexchange.com/questions/5786/how-to-avoid-procrastination-during-the-research-phase-of-my-phd
recommend(5786).foreach(println)

tagHashes = tagHashes.cache
