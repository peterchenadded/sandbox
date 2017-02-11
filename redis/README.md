# Redis Notes

Redis is single threaded, which means that it always executes one command at a time.

Some commands are also atomic which means there are no race conditions when actions are performed on the same key at the same time.


## Useful commands

### Start the server
    redis-server

### Redis command line interface
    redis-cli
    SET name "peter chen"
    GET name
    HELP set
    KEYS n*
    MSET first "first key value" second "second key value"
    MGET first second
    DEL name
    EXPIRE first 10
    TTL first
    
    # Increment/Decrement Commands
    SET counter 100
    INCR counter
    INCRBY counter 5
    DECR counter
    DECRBY counter 100
    
    # List commands
    LPUSH books "Clean Code"
    RPUSH books "Code Complete"
    LLEN books
    LINDEX books 1
    LRANGE books 0 1
    LRANGE books 0 -1
    LPOP books
    RPOP books
    BRPOP books

    # Hash commands
    HSET movie "title" "The GodFather"
    HSET movie "year" 1972 "rating" 9.2 "watchers" 10000000
    HINCRBY movie "watchers" 3
    HGET movie "title"
    HMGET movie "title" "watchers"
    HDEL movie "watchers" 
    HGETALL movie
    HKEYS movie
    HVALS movie
    HSCAN example 0
    
### Simple nodejs client
    # node hello.js
    var redis = require("redis");
    var client = redis.createClient();

    client.set("my_key", "Hello World using Node.js and Redis")
    client.get("my_key", redis.print);
    client.quit();



