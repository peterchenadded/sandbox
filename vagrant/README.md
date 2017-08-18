# Vagrant Notes

## Up and Running

```
vagrant init geerlingguy/centos7

# Add following to get ssh to work

  # Bugfix for "Cable connected: off"
  config.vm.provider :virtualbox do |vm|
    vm.customize [
      "modifyvm", :id,
      "--cableconnected1", "on",
    ]
  end
```

```
vagrant up
vagrant reload
vagrant halt
vagrant suspend
vagrant destroy
vagrant status
vagrant ssh
vagrant ssh-config
```

```
# Version 1
Vagrant::Config.run do |config|
    # V1 Config here
end

# Version 2
Vagrant.configure("2") do |config|
    # V2 config here
end
```
# Vagrant works by using boxes (e.g. centos7) to created vms
# check available boxes
vagrant box list
```

```
# By default the project directory is shared with the machine
vagrant ssh
ls -lrt /vagrant

# Synced folder
config.vm.synced\_folder "/home/osboxes/sandbox" "/sandbox"

# Port forwarding
config.vm.network "forwarded\_port", guest: 80, host: 8080

# sudo python -m SimpleHTTPServer 80
```

```
# Guest can't access host machine
# Hostonly networking allows host machine access to Guest through ip, and other vms on the same host can also access that ip. The guest can also access the host. The ip is the same but the final octet as a 1.
# Bridged networking makes it look like the vm is actaully another separate physical machine on the network. It allows the DHCP to assign an IP to the guest machine.


```
