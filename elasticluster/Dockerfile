# docker build -t commonera/elasticluster:utopic .
# docker run --net=host -i -t commonera/elasticluster:utopic

FROM commonera/node:utopic

RUN apt-get install -y ansible libyaml-dev pandoc markdown python-markdown
RUN git clone https://github.com/gc3-uzh-ch/elasticluster
WORKDIR /elasticluster
RUN git submodule init
RUN git submodule update
RUN python setup.py install
