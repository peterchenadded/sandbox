package jmxbook.ch2;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * Run and opens a listener at http://localhost:9092
 * @author PChen
 *
 */
public class HelloAgent implements NotificationListener {
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
			
			hw.addNotificationListener(this, null, null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void handleNotification(Notification notif, Object handback) {
		LOG.info("Receiving notification...");
		LOG.info(notif.getType());
		LOG.info(notif.getMessage());
	}
	
	public static void main(String args[]) {
		LOG.info("HelloAgent is running");
		HelloAgent agent = new HelloAgent();
	}

}
