package org.ddd.thread.example6;

public class ExceptionThread implements Runnable {

	// org/ddd/thread/exception/ExceptionThread.java
	public static void main(String[] args) {
//		try {
			Thread exceptionThread = new Thread(new ExceptionThread());
			System.out.println("开始启动线程");
			exceptionThread.start();
			System.out.println("线程启动完成");
//		} catch (Exception e) {
//			System.out.println("已经捕获异常（但实际上是不可能执行到这里的！）");
//		}
	}

	public void run() {
		System.out.println("线程已经开始执行任务");
		throw new RuntimeException();
	}
}