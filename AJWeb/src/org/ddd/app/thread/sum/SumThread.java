package org.ddd.app.thread.sum;

public class SumThread implements Runnable {

	public  Long sum=0l;
	public static ThreadLocal<Long> threadSum = new ThreadLocal<Long>();
	//public static ThreadLocal<Session> Session = new ThreadLocal<Session>();
	@Override
	public void run() {
		for(int i=0;i<1000;i++)
		{
			//calc();
			
			synchronized (this) {
				this.sum =this.sum+1;
				if(threadSum.get() ==null)
				{
					threadSum.set(0l);
				}
				else
				{
					threadSum.set(threadSum.get()+1);
				}
			}
			
		}
		System.out.println(Thread.currentThread().getName() +"thread local sum is "+threadSum.get());
		System.out.println(Thread.currentThread().getName() +" is over");
	}
	private synchronized void calc()
	{
		this.sum =this.sum+1;
	}

}
