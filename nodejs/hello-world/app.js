var express = require('express');
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

