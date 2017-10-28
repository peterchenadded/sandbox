package jmxbook.ch3;

import java.rmi.registry.LocateRegistry;
import java.util.HashMap;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnectorServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class JMXBookAgent {
	private MBeanServer server = null;
	static final Logger LOG = LoggerFactory.getLogger(JMXBookAgent.class);
	
	public JMXBookAgent() {
		LOG.info("CREATE the MBeanServer.");
		server = MBeanServerFactory.createMBeanServer("JMXBookAgent");
		
		startHTMLAdapter();
		startRMIConnector();
	}
	
	protected void startHTMLAdapter() {
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		ObjectName adapterName = null;
		
		try {
			adapter.setPort(9092);
			adapterName = new ObjectName("JMXBookAgent:name=html,port=9092");
			server.registerMBean(adapter, adapterName);
			adapter.start();
		} catch (Exception ex){
			LOG.error("Error starting HTML adapter for agent", ex);
		}
	}
	
	protected void startRMIConnector() {
		HashMap<String, Object> env = new HashMap<String, Object>();
		ObjectName connectorName = null;
		
		// http://www.massapi.com/class/jm/JMXServiceURL.html
		try {
			int rmiPort = 2099;
			LocateRegistry.createRegistry(rmiPort);
			String svc = "service:jmx:rmi:///jndi/rmi://localhost:"+rmiPort+"/jmxrmi";
		    JMXServiceURL url = new JMXServiceURL(svc);
		    RMIConnectorServer connector = new RMIConnectorServer(url, null);

			connectorName = new ObjectName("JMXBookAgent:name=RMIConnector");
			server.registerMBean(connector, connectorName);
			connector.start();
		} catch (Exception ex) {
			LOG.error("Error starting RMI adapter", ex);
		}
	}
	
	public static void main(String[] args) {
		LOG.info("START of JMXBook Agent");
		LOG.info("CREATE the agent...");
		JMXBookAgent agent = new JMXBookAgent();
		LOG.info("Agent is ready for service...");
	}
}
