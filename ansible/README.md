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

