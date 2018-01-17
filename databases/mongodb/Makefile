start:
	/opt/mongodb/mongodb/bin/mongod --auth --dbpath /opt/mongodb/mongodb/data/db --sslMode requireSSL --sslPEMKeyFile /opt/mongodb/mongodb/ssl/mongodb.pem --sslAllowConnectionsWithoutCertificates
	#/opt/mongodb/mongodb/bin/mongod --auth --dbpath /opt/mongodb/mongodb/data/db
	#/opt/mongodb/mongodb/bin/mongod --dbpath /opt/mongodb/mongodb/data/db --sslMode requireSSL --sslPEMKeyFile /opt/mongodb/mongodb/ssl/mongodb.pem --sslAllowConnectionsWithoutCertificates

shell:
	#/opt/mongodb/mongodb/bin/mongo --host osboxes --ssl --sslAllowInvalidCertificates --username admin -p
	/opt/mongodb/mongodb/bin/mongo --ssl --sslAllowInvalidCertificates --authenticationDatabase "admin" --username root -p
	#/opt/mongodb/mongodb/bin/mongo --authenticationDatabase "admin" --username root -p

python:
	python test_kvstore_mongodb.py

source:
	source test/bin/activate

