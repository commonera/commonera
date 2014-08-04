# Spark 1.0.0
# Version 1.0.0
#
FROM commonera/apache-hadoop-hdfs:1.2.1


ENV SCALA_VERSION 2.10.3
ENV SPARK_VERSION 1.0.0
ENV SCALA_HOME /opt/scala-$SCALA_VERSION
ENV SPARK_HOME /opt/spark-$SPARK_VERSION
ENV PATH $SPARK_HOME:$SCALA_HOME/bin:$PATH

# Install Scala
ADD scala-2.10.3.tgz /opt/
#RUN (cd /opt && tar -xvf /scala-2.10.3.tgz)

# Install Spark
ADD spark-$SPARK_VERSION-bin-hadoop1.tgz /opt/
#RUN (cd / && gunzip < spark-$SPARK_VERSION-bin-hadoop1.tgz)|(cd /opt && tar -xvf -)
RUN (ln -s /opt/spark-$SPARK_VERSION-bin-hadoop1 /opt/spark-$SPARK_VERSION)

# Add Shark config files and configure script
ADD files /root/spark_files
