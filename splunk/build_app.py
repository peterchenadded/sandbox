import jinja2
import os
import shutil
import ConfigParser


templates_dir = "templates"
package_dir = "package"
root_dir = os.path.dirname(os.path.abspath(__file__))
variables_file = "variables.conf"

# Read in configs
config = ConfigParser.ConfigParser()
config.read(variables_file)
variables_dict = {}

for section in config.sections():
    for (name, value) in config.items(section):
        print "section=%s, name=%s, value=%s" % (section, name, value)
        variables_dict[name] = value

# Loaded
print "Loaded: %s" % (variables_dict)

# Error out on undefined variables
#env = jinja2.Environment( loader=jinja2.FileSystemLoader(templates_dir), undefined=jinja2.StrictUndefined)
env = jinja2.Environment( loader=jinja2.FileSystemLoader(templates_dir))

# Remove package directory and regenerate package dir
shutil.rmtree(package_dir)
shutil.copytree('templates', 'package')

# Loop through templates and override files
for template in env.list_templates():
    with open(os.path.join(root_dir, package_dir, template), 'w') as f2:
                slug = env.get_template(template)
                slug = slug.render(variables_dict)
                print slug
                f2.write(slug)

