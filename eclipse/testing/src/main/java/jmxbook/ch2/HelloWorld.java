package jmxbook.ch2;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class HelloWorld extends NotificationBroadcasterSupport implements HelloWorldMBean {
	private String greeting = null;
	
	public HelloWorld() {
		this.greeting = "Hello World! I am a standard MBean";
	}
	
	public HelloWorld(String greeting) {
		this.greeting = greeting;
	}

	@Override
	public void setGreeting(String greeting) {
		this.greeting = greeting;
		
		Notification notification = new Notification(
				"jmxbook.ch2.helloWorld.test", this, -1,
				System.currentTimeMillis(), greeting);
		
		sendNotification(notification);

	}

	@Override
	public String getGreeting() {
		return this.greeting;
	}

	@Override
	public void printGreeting() {
		System.out.println(greeting);
	}

}
