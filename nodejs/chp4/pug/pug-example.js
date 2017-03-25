var pug = require('pug'),
  fs = require('fs');

var data = {
  title: 'Practical Node.js',
  author: {
    twitter: "@peterchenadded",
    name: "Azat"
  },
  tags: ['express', 'node', 'javascript']
}

data.body = process.argv[2];

fs.readFile('pug-example.pug', 'utf-8', function(error, source) {
  var template = pug.compile(source);
  var html = template(data)
  console.log(html)
});

