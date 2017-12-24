# Installation
wget https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-rhel70-3.6.0.tgz
tar -zxvf mongodb-linux-x86_64-rhel70-3.6.0.tgz
sudo mkdir /opt/mongodb
mv mongodb-linux-x86_64-rhel70-3.6.0 /opt/mongodb
ln -s mongodb-linux-x86_64-rhel70-3.6.0.tgz mongodb
mkdir -p mongodb/data/db

# Makefile
start:
	./bin/mongod --dbpath /opt/mongodb/mongodb/data/db

# Verification
nc localhost 27017

# Main features
1. Replication
2. Sharding
3. Speed and durability
4. Indexes
5. Schemaless - document data model
6. Ad hoc queries

# Javascript shell
> use my_database
> db.users.insert({name: "Kyle"})
> db.users.find()





