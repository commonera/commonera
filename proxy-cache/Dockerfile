# docker build -t commonera/proxy-cache:utopic .
# docker run --privileged -v /cache/proxy/squid3:/var/spool/squid3 --rm -i -t proxy /usr/local/bin/autostart.sh

FROM ubuntu:utopic

RUN apt-get update && apt-get install -y squid3 iptables
ADD squid.conf /etc/squid3/squid.conf
ADD autorun.sh /usr/local/bin/autorun.sh
ADD start_squid.sh /usr/local/bin/start_squid.sh

EXPOSE 3128

#CMD /usr/local/bin/autorun.sh
CMD ["/usr/local/bin/start_squid.sh"]
