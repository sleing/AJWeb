package org.ddd.app.thread;

public class ThreadTest2 {
	
	@Deprecated
	public static void main(String[] args) {
		System.out.println("this is a thread with name "
				+ Thread.currentThread().getName());
		Runnable runnable1 = new TestThread2();
		Thread testThread1 = new Thread(runnable1);
		testThread1.setName("childThread1");
		testThread1.setPriority(Thread.NORM_PRIORITY+1);
		testThread1.setDaemon(false);
		testThread1.start();
		
		Runnable runnable2 = new TestThread2();
		Thread testThread2 = new Thread(runnable2);
		//testThread2.stop();
		testThread2.setName("childThread2");
		testThread2.setPriority(Thread.NORM_PRIORITY-1);
		testThread2.setDaemon(false);
		testThread2.start();
		
		System.out.println("main thread is over");	
	}

}
