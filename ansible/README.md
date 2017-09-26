# Installing Ansible

```
# Install ansible
sudo pip install ansible

# Dependencies
sudo apt-get install python-dev
sudo apt-get install libffi-dev
```

# Commands

```
# Ansible version
ansible --version

# Ansible read hosts file (defaults to /etc/ansible/hosts)
ansible -i <HOSTS_FILE>

# Ping servers
ansible -i hosts example -m ping

# Check for free memory
ansible example -a "free -m"

```

# Install Vagrant and VirtualBox

```
sudo apt-get install vagrant
sudo apt-get install virtualbox

vagrant version
vboxmanage -version

```

# Setting up a CentOS vm

```
mkdir vagrant
vagrant box add geerlingguy/centos7
vagrant init geerlingguy/centos7
vagrant up
vagrant halt
vagrant destory
vagrant ssh-config

# For networking to work
Ensure the network "Cable connected" setting is enabled by going to "Settings" in virtualbox for the vm or add in following after the config.vm.box line

  # Bugfix for "Cable connected: off"
  config.vm.provider :virtualbox do |vm|
    vm.customize [
      "modifyvm", :id,
      "--cableconnected1", "on",
    ]
  end
```

# Running a playbook

```
ansible-playbook playbook.yml
# Ask for password
ansible-playbook playbook.yml --ask-become-pass
# Run in parallel
ansible-playbook playbook.yml -f 10
```

# Use vagrant provisioning with ansible

```
# Add below to Vagrantfile
config.vm.provision "ansible" do |ansible|
  ansible.playbook = "playbook.yml"
end
```

```
# Sample playbook.yml
---
- hosts: all
  sudo: yes
  tasks:
  - yum: name=ntp state=present
  - service: name=ntpd state=started enabled=yes
```

```
# Run playbook.yml
vagrant provision
```

# Module help

```
ansible-doc command
ansible-doc debug
ansible-doc shell
```

# Inventory

ansible_* are normally behaviour inventory variables

```
[web]
mastery.example.name ansible_host=192.168.10.25

[dns]
backend.example.name

[database]
backend.example.name

[frontend:children]
web

[backend:children]
dns
database

[web:vars]
http_port=88
proxy_timeout=5

[backend:vars]
ansible_port=314

[all:vars]
ansible_ssh_user=otto
```
