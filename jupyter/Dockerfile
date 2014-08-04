# docker build -t commonera/jupyter:utopic .
# docker run --net=host -i -t commonera/jupyter:utopic

FROM commonera/node:utopic

RUN git clone https://github.com/jupyter/multiuser-server jupyter-multiuser-server
WORKDIR /jupyter-multiuser-server
RUN npm install
RUN pip install -r requirements.txt
CMD python -m multiuser
