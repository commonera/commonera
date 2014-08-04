# docker build -t commonera/ipython:stable .

# http://askubuntu.com/questions/32007/how-to-find-manually-installed-packages
#   aptitude search '?installed ?not(?automatic)' | grep -i python

FROM commonera/python:utopic

WORKDIR /
RUN apt-get install -y libzmq-dev
RUN pip install --upgrade ipython[all]
WORKDIR /
