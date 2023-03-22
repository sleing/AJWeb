package org.ddd.app.thread.sum;

public class SumThreadTest {

	public static void main(String[] args) throws InterruptedException {

		SumThread sumThread = new SumThread();
		Thread thread1 = new Thread(sumThread);
		thread1.setName("thread1");
		
		Thread thread2 = new Thread(sumThread);
		thread2.setName("thread2");
		
		thread1.start();
		thread2.start();
		
		Thread.sleep(4000);
		
		System.out.println(sumThread.sum);
		
	}

}
