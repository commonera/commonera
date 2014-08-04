FROM ubuntu:utopic

# Setup a volume for data
VOLUME ["/data"]

# install a few other useful packages plus Open Jdk 7
RUN apt-get update && apt-get upgrade -y && apt-get install -y less openjdk-7-jre-headless net-tools vim-tiny sudo openssh-server iputils-ping python2.7

# Install Hadoop
ADD hadoop_1.2.1-1_x86_64.deb /root/
RUN dpkg -i /root/hadoop_1.2.1-1_x86_64.deb

# Docker messes up /etc/hosts and adds two entries for 127.0.0.1
# we try to recover from that by giving /etc/resolv.conf and therefore
# the nameserver priority
RUN sed -i s/"files dns"/"dns files"/ /etc/nsswitch.conf

# add Hadoop config file templates
ADD files /root/hadoop_files

# Set JAVA_HOME
ENV JAVA_HOME /usr/lib/jvm/java-7-openjdk-amd64
