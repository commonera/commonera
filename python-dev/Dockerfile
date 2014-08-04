# docker build -t commonera/ipython:dev .

# http://askubuntu.com/questions/32007/how-to-find-manually-installed-packages
#   aptitude search '?installed ?not(?automatic)' | grep -i python

FROM commonera/python:utopic

WORKDIR /
RUN git clone --recursive https://github.com/ipython/ipython.git
WORKDIR ipython
#RUN pip install --upgrade -e ".[notebook"
RUN pip install --upgrade -e ".[all]"
WORKDIR /
