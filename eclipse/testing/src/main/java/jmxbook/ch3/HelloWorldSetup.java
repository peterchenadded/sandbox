package jmxbook.ch3;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldSetup {
	static final Logger LOG = LoggerFactory.getLogger(HelloWorldSetup.class);

	public HelloWorldSetup() {
		try {
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:2099/jmxrmi");
			JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
			
			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
			
			ObjectName hwName = new ObjectName("JMXBookAgent:name=helloWorld");

			mbsc.createMBean("jmxbook.ch2.HelloWorld", hwName);
			mbsc.invoke(hwName, "printGreeting", null, null);

		} catch(Exception ex) {
			LOG.error("JMX error", ex);
		}
	}

	public static void main(String args[]) {
		HelloWorldSetup setup = new HelloWorldSetup();
	}
}
