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
