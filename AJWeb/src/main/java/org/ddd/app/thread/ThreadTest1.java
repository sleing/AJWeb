package org.ddd.app.thread;

public class ThreadTest1 {

	public static void main(String[] args) {
		System.out.println("this is a thread with name "
				+ Thread.currentThread().getName());
		Thread testThread1 = new TestThread();
		testThread1.setName("childThread1");
		testThread1.setPriority(Thread.NORM_PRIORITY+1);
		testThread1.setDaemon(true);
		testThread1.start();
		
		Thread testThread2 = new TestThread();
		testThread2.setName("childThread2");
		testThread2.setPriority(Thread.NORM_PRIORITY-1);
		testThread2.setDaemon(true);
		testThread2.start();
		
		System.out.println("main thread is over");	
	}

}
