package org.ddd.app.thread;

public class TestThread extends Thread {

	@Override
	public void run() {
		for(int i=1;i<100;i++)
		{
			for(int j=1;j<1000000000;j++)
			{
				float a = j/i;
			}
			System.out.println("child thread is"
					+ Thread.currentThread().getName() +"\t"+i);
		}
	}
}
