var express = require('express');
var http = require('http');
var path = require('path');

var app = express();

app.set('appName', 'hello-world');
app.set('port', process.env.PORT || 3000);
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

// middleware

// routes
app.all('*', function(req, res) {
  res.render('index', {msg: 'Welcome to the Practical Node.js!'});
});

// start the server
var server = http.createServer(app);
var boot = function() {
  server.listen(app.get('port'), function() {
    console.info('Express server listening on port ' + app.get('port'));
  });
}

var shutdown = function() {
  server.close();
}

if(require.main == module) {
  boot();
} else {
  console.info('Running app as a module')
  exports.boot = boot;
  exports.shutdown = shutdown;
  exports.port = app.get('port');
}
