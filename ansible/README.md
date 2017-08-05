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
```

# Setting up a CentOS vm

```
mkdir vagrant
vagrant box add geerlingguy/centos7
vagrant init geerlingguy/centos7
vagrant up
```

