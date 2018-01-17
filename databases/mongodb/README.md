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

# Disable Transparent huge pages
vim /etc/systemd/system/disable-transparent-huge-pages.service
systemctl enable disable-transparent-huge-pages
systemctl start disable-transparent-huge-pages
systemctl status disable-transparent-huge-pages
cat /sys/kernel/mm/transparent_huge_page/enabled
cat /sys/kernel/mm/transparent_huge_page/defrag

```
[Unit]
Description=Disable Transparent Huge Pages

[Service]
Type=oneshot
ExecStart=/usr/bin/sh -c "/usr/bin/echo "never" | tee /sys/kernel/mm/transparent_hugepage/enabled"
ExecStart=/usr/bin/sh -c "/usr/bin/echo "never" | tee /sys/kernel/mm/transparent_hugepage/defrag"

[Install]
WantedBy=multi-user.target
```

# Tutorial
use tutorial
db.users.insert({username: "smith"})
db.users.find()
db.users.count()
db.users.find({username: "jones"})
db.users.find({$or: [{username: "smith"}, {username: "jones"}]})
db.users.update({username: "smith"}, {$set, {country: "Canada"}})
db.users.update({username: "smith"}, {country: "Canada})
db.users.update({username: "smith"}, {$unset: {country: 1}})
db.users.find().pretty()
db.users.update({"favorite.movies": "Casablanca"}, {$addToSet: {"favorite.movies": "The Maltese Falcon"}}, false, true)
db.foo.remove()
db.users.remove({"favorites.cities": "Cheyenne"})
db.users.drop()
help
db.help()

# Indexes
for(i=0; i<20000; i++) {
    db.numbers.save({num: i})
}
db.numbers.count()
db.numbers.find()

db.numbers.find({num: {"$gt": 19995}})
db.numbers.find({num: {"$gt": 19995}}).explain("executionStats")

1. totalDocsExamined
2. nReturned
3. totalKeysExamined (number of index entries scanned)
4, executionTimeMillis

db.numbers.createIndex({num: 1})
db.numbers.getIndexes()

# Performance
function test_perf(max_docs, bulk_size) {
	var docs = []
	for(i=0; i< max_docs; i++) {
	    docs.push({_id: i})
	    if(docs.length % bulk_size == 0) {
		db.test.insertMany(docs)
		docs = []
	    }
	}
        if(docs.length > 0) db.test.insert_many(docs)
}

# Administration
show dbs
show collections
db.stats()
db.test.stats()

# Installing ruby driver
ruby --version
gem --version
gem install mongo

# Installing python driver
yum install epel-release
yum install python-pip
virtualenv test
source activate
pip install pymongo

# Setup ssl
openssl req -newkey rsa:2048 -new -x509 -days 365 -nodes -out mongodb-cert.crt -keyout mongodb-cert.key
cat mongodb-cert.key mongodb-cert.crt > mongodb.pem

mongod --sslMode requireSSL --sslPEMKeyFile <pem> <additional options>

