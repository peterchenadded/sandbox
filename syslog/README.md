# Installation

sudo apt-get install syslog-ng

# Configuration file

/etc/syslog-ng/syslog-ng.conf

# Restart

/etc/init.d/syslog-ng restart
systemctl restart syslog-ng
/usr/sbin/syslog-ng -d

# Setting up a network listener

uncomment the below line

source s\_net { tcp(ip(127.0.0.1) port(1000)); };

add below lines

destination d\_test { file("/var/log/test"); };

log { source(s\_net); destination(d\_test); };

# Sample logger command

logger -T -P 1000 -n 127.0.0.1 hello world

# Filters

https://www.balabit.com/documents/syslog-ng-ose-latest-guides/en/syslog-ng-ose-guide-admin/html/reference-filters.html

filter f\_dbg { level(debug); };

log { source(s\_src); filter(f\_debug); destination(d\_debug); };

# Logs

log {
 source(s1); source(s2); ...
 optional\_element(filter1|parser1|rewrite1);
 optional\_element(filter2|parser2|rewrite2);...
 destination(d1); destination(d2); ...
 flags(flag1[, flag2...]);
 };


# Running as non-root

# Change - /lib/systemd/system/syslog-ng.service
ExecStart=/usr/sbin/syslog-ng -u splunk -g splunk --persist-file /opt/splunk/syslog-ng/syslog-ng.persist --pidfile /opt/splunk/syslog-ng/syslog-ng.pid -F
