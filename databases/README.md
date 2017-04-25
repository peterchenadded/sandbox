# Postgresql

## Installation

See https://tecadmin.net/install-postgresql-server-on-ubuntu/

## Connect to it

sudo su - postgres
psql

````
# Get help on command
\h CREATE INDEX

Column - A domain of values of a certain type
Row - An object comprised as a set of column values
Table - A set of rows with the same columns
Primary key - Unique value that pin points a specific row
CRUD - Create, Read, Update, Delete
SQL - Structured Query Language
Join - Combining two tables into one by some matching columns
Left join - Join but keep everything on the left side
Index - A data structure to optimize selection of rows
B-tree - A good standard index where values are stored as a balanced tree data structure
````

# Riak

Installation steps http://docs.basho.com/riak/kv/2.2.3/setup/installing/debian-ubuntu/
Depends on Erlang http://docs.basho.com/riak/kv/2.2.3/setup/installing/source/erlang/#installing-on-debian-ubuntu

cd ~/workspace/riak-2.2.3

dev/dev1/bin/riak start
dev/dev2/bin/riak start
dev/dev3/bin/riak start

dev/dev2/bin/riak-admin join -f dev@127.0.0.1

./dev2/etc/riak.conf:listener.http.internal = 127.0.0.1:10028
./dev3/etc/riak.conf:listener.http.internal = 127.0.0.1:10038
./dev1/etc/riak.conf:listener.http.internal = 127.0.0.1:10018

# Ping the server
curl http://localhost:10018/ping

# Get for key in bucket
curl -I http://localhost:10018/riak/no_bucket/no_key

# Put a key in a bucket
curl -v -X PUT http://localhost:10018/riak/favs/db -H "Content-Type: text/html" -d "<html><body><h1>My new favorite DB is RIAK</h1></body></html>"

# See list of buckets
curl -X GET http://localhost:10018/riak?buckets=true

# Delete key
curl -i -X DELETE http://localhost:8091/riak/animals/6VZc2o7zKxq2B34kJrm1S0ma3PO

# Get list of keys
curl http://localhost:10018/riak/animals?keys=true

Link: </riak/bucket/key>; riaktag=\"whatever\"

curl -X PUT http://localhost:10018/riak/cages/1 -H "Content-Type: application/json" -H "Link: </riak/animals/polly>; riaktag=\"contains\"" -d '{"room" : 101}'

curl http://localhost:8091/riak/cages/1/_,_,_

link criteria: bucket, tag, keep

# Has an http interface and also a protocal buffer interface
# http interface: 10018
# protocal buffer interface: 10017

# Ruby

## Installation
sudo apt-get install ruby-full
sudo apt-get install rubygems

# HBase

Column oriented database based on BigTable a proprietary database developed by Google. Now an top-level Apache project. Uses write-ahead logging and distributed configuration, it can quickly recover from individual server failures.

Built on hadoop

## Installation
- Requires java
- Installed into hduser

## Starting hadoop
- /home/hduser/hadoop/sbin/start-dfs.sh
- /home/hduser/hadoop/sbin/start-yarn.sh
## Starting hbase
- /home/hduser/hbase/bin/start-hbase.sh

## Hbase shell
- /home/hduser/hbase/bin/hbase shell

### Creating a table
A table is basically a big map in HBase

create 'wiki', 'text'

creates a table called wiki with a single column family called text. Unlike a relational database, here a column is specific to the row that contains it. When we start adding rows, we'll add columns to store data in the same time.

put 'wiki', 'Home', 'text:', 'Welcome to the wiki!'
get 'wiki', 'Home', 'text:'

in HBase versioning is baked right in

disable 'wiki'

${HBASE_HOME}/bin/hbase shell <your_script> [<optional_arguments> ...]

Bloom filters efficiently answer the question, "Have I ever seen this thing before?" They offer a fast way of determining whether data exists before incurring an expensive disk read.

hbase distributes keys by regions. A region is a chunk of rows identified by the starting key and ending key that never overlap with other regions.

hbase uses WALS (write ahead logging) to provide against node failures. This is a typical disaster recovery technique. This technique is also known as journaling. In HBase, logs are appended to the WAL before any edit operations (pu and increment) are persisted to disk. For performance reasons there writes are buffered and written to disk in chunks.

# Mongodb

## Installation
sudo apt-get install mongodb

## CLI
mongo book
show collections

db.towns.insert({name: "New York", .... })
db.towns.find()
db.help()
db.towns.help()

db.towns.find({"_id" : ObjectId("58f355759d5d387d936304a1")}, {name: 1})

## Find indexes
db.system.indexes.find()

## Limit number of results
db.phones.find().limit(2)

## Pass given function to server to run
db.eval(update_area)



