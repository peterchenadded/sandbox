# vim: ts=2
inventory_file = hosts

nodejs:
	ansible-playbook -i $(inventory_file) nodejs_playbook.yml --extra-vars=" node_apps_location=/usr/local/opt/node" --step
