# Nodejs

## Checking the installation
    node -v
    npm -v
    node -e "console.log(new Date())"
    node -e "console.log(process.pid)"

## Differences with javascript in the browser
    Browser javascript puts everything into its global scope. Node.js behaves differently, with everything being local by default.
    document object does not exist in Node.js
    
## Exporting objects
    exports.messages = messages;

## Importing objects
    var messages = require('./routes/messages.js');

## Core modules
    http, util, querystring, url, fs

## Debugging
    include debugger; in source code
    use node debugger file.js to debug
    next, cont, step, out, watch(expression)

    export DEBUG=*

## Express
    npm init
    npm install express@4.1.2 --save
    npm list 

    node_modules - dependencies
    views - jade files
    routes - request handlers
    db - seed data and scripts for mongodb
    public - all static files

## Sample app.js
    var http = require('http');
    var path = require('path');

    var app = express();

    app.set('appName', 'hello-world');
    app.set('port', process.env.PORT || 3000);
    app.set('views', path.join(__dirname, 'views'));
    app.set('view engine', 'jade');

    // middleware

    // routes
    app.all('*', function(req, res) {
      res.render('index', {msg: 'Welcome to the Practical Node.js!'});
    });

    // start the server
    http.createServer(app).listen(app.get('port'), function() {
        console.log('Express.js server listening on port ' + app.get('port'));
    });

## Sample views/index.jade
    h1
    p= msg

## Using mocha
    npm install -g mocha
    mocha tests

    mkdir tests
    # tests/index.js file

## Sample mocha test
    // boot, shutdown and port need to be module.<variable> in app.js
    var boot = require('../app').boot,
      shutdown = require('../app').shutdown,
      port = require('../app').port,
      superagent = require('superagent'),
      expect = require('expect.js');

    describe('server', function() {
      // start the server
      before(function() {
        boot();
      });

      // check response from server is 200
      describe('homepage', function() {
        it('should respond to GET', function(done) {
          superagent
          .get('http://localhost:'+port)
          .end(function(err, res) {
            expect(res.status).to.equal(200);
            done()
          })
        })
      }); 

      // shutdown the server
      after(function() {
        shutdown();
      });
    });


