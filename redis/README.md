# Redis Notes

## Useful commands

### Start the server
    redis-server

### Redis command line interface
    redis-cli
    SET name "peter chen"
    GET name
    HELP set
    KEYS n*
    
### Simple nodejs client
    var redis = require("redis");
    var client = redis.createClient();

    client.set("my\_key", "Hello World using Node.js and Redis")
    client.get("my\_key", redis.print);
    client.quit();





