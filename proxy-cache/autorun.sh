#!/bin/bash

squid3 -N &
ifconfig eth0
sleep 2
tail -f /var/log/squid3/*log &
/bin/bash -l
