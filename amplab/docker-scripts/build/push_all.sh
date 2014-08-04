#!/bin/bash

if [[ "$USER" != "root" ]]; then
    echo "please run as: sudo $0"
    exit 1
fi

image_list=( "apache-hadoop-hdfs" "dnsmasq" "spark-master" "spark-worker" "spark-shell" "shark-master" "shark-worker" "shark-shell" )

IMAGE_PREFIX="commonera/"

# NOTE: the order matters but this is the right one
for i in ${image_list[@]}; do
	echo docker push ${IMAGE_PREFIX}${i}
        docker push ${IMAGE_PREFIX}${i}
done
