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


