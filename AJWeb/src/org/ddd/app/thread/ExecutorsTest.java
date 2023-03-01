package org.ddd.app.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.ddd.app.thread.sum.SumThread;

 

public class ExecutorsTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new SumThread());
//		Executors.newFixedThreadPool(nThreads);
		List<Future> futures = new ArrayList<Future>();
		for (int i = 0; i < 10; i++) {
			PrimeNumberCallable callable = new PrimeNumberCallable();
			Future future = (Future) service.submit(callable);
			futures.add(future);
		}

		while (true) {
			int doneCount = 0;
			for (Future future : futures) {
				if (future.isDone()) {
					doneCount++;
				}
			}
			if (doneCount == 10) {
				break;
			} else {
				try{
					Thread.sleep(1000);
				}
				catch (Exception e){
					System.out.println("����");
				}
			}
		}

		for (Future future : futures) {
			try{
				future.get();
			}
			catch (Exception e){
				System.out.println("����");
			}
		}


	}
}
