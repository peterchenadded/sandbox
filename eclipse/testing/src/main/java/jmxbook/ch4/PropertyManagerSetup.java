package jmxbook.ch4;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jmxbook.ch3.HelloWorldSetup;

public class PropertyManagerSetup {
	static final Logger LOG = LoggerFactory.getLogger(PropertyManagerSetup.class);

	public PropertyManagerSetup() {
		try {
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:2099/jmxrmi");
			JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
			
			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
			
			ObjectName propertyName = new ObjectName("JMXBookAgent:name=property");

			Object[] parameters = { "resources/test.properties" };
			String[] signature = { "java.lang.String" };

			mbsc.createMBean("jmxbook.ch4.PropertyManager", propertyName, parameters, signature);
		} catch(Exception ex) {
			LOG.error("JMX error", ex);
		}
	}

	public static void main(String args[]) {
		PropertyManagerSetup setup = new PropertyManagerSetup();
	}
}
