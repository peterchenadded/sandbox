package jmxbook.ch2;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class HelloAgent {
	private MBeanServer mbs = null;

	static final Logger LOG = LoggerFactory.getLogger(HelloAgent.class);

	public HelloAgent() {
		mbs = MBeanServerFactory.createMBeanServer("HelloAgent");
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		
		HelloWorld hw = new HelloWorld();
		ObjectName adapterName = null;
		ObjectName helloWorldName = null;
		
		try {
			helloWorldName = new ObjectName("HelloAgent:name=helloWorld1");
			mbs.registerMBean(hw, helloWorldName);
			
			adapterName = new ObjectName("HelloAgent:name=htmladapter,port=9092");
			adapter.setPort(9092);
			mbs.registerMBean(adapter, adapterName);
			adapter.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		LOG.info("HelloAgent is running");
		HelloAgent agent = new HelloAgent();
	}

}
