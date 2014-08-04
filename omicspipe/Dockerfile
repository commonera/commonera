# docker build -t commonera/omicspipe:utopic .
# docker run --net=host -i -t commonera/omicspipe

FROM commonera/node:utopic

RUN hg clone https://bitbucket.org/sulab/omics_pipe omicspipe
RUN pip install -e hg+https://bitbucket.org/sulab/omics_pipe#egg=omics_pipe
