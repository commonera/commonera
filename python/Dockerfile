# docker build -t commonera/python:utopic .

# http://askubuntu.com/questions/32007/how-to-find-manually-installed-packages
#   aptitude search '?installed ?not(?automatic)' | grep -i python

FROM commonera/buildpack-deps:utopic

RUN apt-get install -y \
		python-pandas \
		ipython-notebook \
    python-software-properties \
    software-properties-common \
    python-pip
    python-lxml
