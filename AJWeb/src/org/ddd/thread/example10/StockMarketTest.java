package org.ddd.thread.example10;

import java.io.IOException;

//org/ddd/thread/unsynchstock/StockMarketTest.java
public class StockMarketTest {
	public static void main(String[] args) throws IOException {
		StockMarket stockMarket = new StockMarket(STOCK_ACCOUNT_COUNT,
				INITIAL_STOCKS);
		stockMarket.dealCount = 0;
		stockMarket.startN = System.nanoTime();
		int i;
		for (i = 0; i < BROKER_THREAD_COUNT; i++) {
			Broker broker = new Broker(stockMarket, i, INITIAL_STOCKS);
			broker.setDaemon(true);
			broker.setName(String.format(" broker %5d ", i));
			broker.start();
		}
		System.in.read();
	}

	public static final int BROKER_THREAD_COUNT = 10;
	public static final int STOCK_ACCOUNT_COUNT = 10;
	public static final long INITIAL_STOCKS = 10;
}