#!/bin/bash

CURDIR=$(pwd)
BASEDIR=$(cd $(dirname $0); pwd)"/.."
#dir_list=( "dnsmasq" "apache-hadoop-hdfs" "spark-0.7.3" "shark-0.7.0" "spark-0.8.0" "spark-0.9.0" "shark-0.8.0" )
dir_list=( "dnsmasq" "apache-hadoop-hdfs" "spark-1.0.0")

(cd apache-hadoop-hdfs/;
if [ ! -e hadoop_1.2.1-1_x86_64.deb ]; then
    wget http://mirror.sdunix.com/apache/hadoop/common/hadoop-1.2.1/hadoop_1.2.1-1_x86_64.deb
fi
)

(cd spark-1.0.0/spark-base/ ;
if [ ! -e scala-2.10.3.tgz ]; then
    wget http://www.scala-lang.org/files/archive/scala-2.10.3.tgz
fi
)

(cd spark-1.0.0/spark-base/ ;
if [ ! -e spark-1.0.0-bin-hadoop1.tgz ]; then
    wget http://d3kbcqa49mib13.cloudfront.net/spark-1.0.0-bin-hadoop1.tgz
fi
)

export IMAGE_PREFIX="commonera/"
#"commonera/"

# NOTE: the order matters but this is the right one
for i in ${dir_list[@]}; do
	echo building $i;
	cd ${BASEDIR}/$i
        cat build
        ./build
done
cd $CURDIR
