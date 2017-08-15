inventory_file = hosts
setup_syslog:
	#export ANSIBLE_KEEP_REMOTE_FILES=1
	ansible-playbook -i $(inventory_file) setup_syslog.yml #--ask-become-pass
	#sleep 5
	#ansible -i hosts ubuntu_slave -m shell -a "ps -ef | grep syslog"

hosts_args:
	ansible-playbook -i $(inventory_file) playbook.yml --limit webservers
	ansible-playbook -i $(inventory_file) playbook.yml --check
	ansible-playbook -i $(inventory_file) playbook.yml --remote-user=johndoe

