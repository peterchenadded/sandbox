inventory_file = hosts

run:
	ansible-playbook -i $(inventory_file) drupal_playbook.yml

vault:
	ansible-vault encrypt api_key.yml

run_vault:
	ansible-playbook vault_playbook.yml --ask-vault-pass
