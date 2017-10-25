package com.peter.testing;

public class Counting {
	public static void main(String[] args) throws InterruptedException {
		class Counter {
			private int count = 0;
			// below implemenation causes a race condition
			// and results will be different every run
			//public void increment() { ++count; }
			
			// new implementation by locking access to the method
			// only one thread can enter this method
			public synchronized void increment() { ++ count; }
			
			public int getCount() { return count; }
		}
		
		final Counter counter = new Counter();
		
		class CountingThread extends Thread {
			public void run() {
				for(int x = 0; x < 10000; ++x)
					counter.increment();
			}
		}

		CountingThread t1 = new CountingThread();
		CountingThread t2 = new CountingThread();
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(counter.getCount());
	}

}
