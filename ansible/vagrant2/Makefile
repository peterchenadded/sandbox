inventory_file = hosts

vagrant_up:
	vagrant up

vagrant_halt:
	vagrant halt

vagrant_status:
	vagrant status

# default module is command
# -a = arguments
# -i = inventory file
# multi = server group
hosts_hostname:
	ansible -i $(inventory_file) multi -a "hostname"

hosts_fork:
	ansible -i $(inventory_file) multi -a "hostname" -f 1

hosts_df:
	ansible -i $(inventory_file) multi -a "df -h"

hosts_free:
	ansible -i $(inventory_file) multi -a "free -m"


hosts_date:
	ansible -i $(inventory_file) multi -a "date"

hosts_setup:
	ansible -i $(inventory_file) multi -m setup

# -s = run operations with sudo (nopasswd)
hosts_ntp:
	ansible -i $(inventory_file) multi -s -m yum -a "name=ntp state=present"
	ansible -i $(inventory_file) multi -s -m service -a "name=ntpd state=started enabled=yes"

hosts_ntp_sync:
	ansible -i $(inventory_file) multi -s -a "service ntpd stop"
	ansible -i $(inventory_file) multi -s -a "ntpdate -q 0.rhel.pool.ntp.org"
	ansible -i $(inventory_file) multi -s -a "service ntpd start"

hosts_app:
	ansible -i $(inventory_file) app -s -m yum -a "name=MySQL-python state=present"
	ansible -i $(inventory_file) app -s -m yum -a "name=python-setuptools state=present"
	ansible -i $(inventory_file) app -s -m easy_install -a "name=django"

hosts_app_django:
	ansible -i $(inventory_file) app -s -a "python -c 'import django; print django.get_version()'"

hosts_db:
	ansible -i $(inventory_file) db -s -m yum -a "name=mariadb-server state=present"
	ansible -i $(inventory_file) db -s -m service -a "name=mariadb state=started enabled=yes"
	ansible -i $(inventory_file) db -s -a "iptables -F"
	ansible -i $(inventory_file) db -s -a "iptables -A INPUT -s 192.168.60.0/24 -p tcp -m tcp --dport 3306 -j ACCEPT"
	

hosts_db_mariadb:
	ansible -i $(inventory_file) db -s -m yum -a "name=MySQL-python state=present"
	ansible -i $(inventory_file) db -s -m mysql_user -a "name=django host=% password=12345 priv=*.*:ALL state=present"

hosts_ntpd_limit:
	ansible -i $(inventory_file) app -s -a "service ntpd restart" --limit "192.168.60.4"
	# wildcard
	ansible -i $(inventory_file) app -s -a "service ntpd restart" --limit "*.4"
	# regex
	ansible -i $(inventory_file) app -s -a "service ntpd restart" --limit ~".*\.4"

hosts_app_group:
	ansible -i $(inventory_file) app -s -m group -a "name=admin state=present"
	ansible -i $(inventory_file) app -s -m user -a "name=johndoe group=admin createhome=yes"
	# Delete account
	#ansible -i $(inventory_file) app -s -m user -a "name=johndoe state=absent remove=yes"

hosts_file:
	ansible -i $(inventory_file) app -m stat -a "path=/etc/environment"

hosts_file_copy:
	ansible -i $(inventory_file) multi -m copy -a "src=/etc/hosts dest=/tmp/hosts"

hosts_file_fetch:
	ansible -i $(inventory_file) multi -s -m fetch -a "src=/etc/hosts dest=/tmp"
	# copying files from single host
	#ansible -i $(inventory_file) multi -s -m fetch -a "src=/etc/hosts dest=/tmp flat=yes"

hosts_file_directory:
	ansible -i $(inventory_file) multi -s -m file -a "dest=/tmp/test mode=644 state=directory"
	# symbolic link
	#ansible -i $(inventory_file) multi -s -m file -a "src=/src/symlink dest=/dest/symlink owner=root group=root state=link"
	# delete
	#ansible -i $(inventory_file) multi -s -m file -a "dest=/tmp/test state=absent"
	
hosts_yum_update:
	# -B <seconds>: Max time to run
	# -P <seconds>: Wait between polling for update of status
	ansible -i $(inventory_file) multi -s -B 3600 -a "yum -y update"
	# Check status
	ansible -i $(inventory_file) multi -s -m async_status -a "jid=XXXXX"

