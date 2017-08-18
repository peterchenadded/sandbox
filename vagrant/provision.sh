#!/bin/bash

echo "Installing Apache and setting it up..."
apt-get update
apt-get install -y apache2
rm -rf /var/www/html
ln -fs /vagrant /var/www/html
