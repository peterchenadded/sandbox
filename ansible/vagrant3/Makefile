inventory_file = hosts

run:
	ansible-playbook -i $(inventory_file) drupal_playbook.yml

vault:
	ansible-playbook -i $(inventory_file) vault_playbook.yml --ask-vault-pass

prompt:
	ansible-playbook -i $(inventory_file) prompts_playbook.yml
	
