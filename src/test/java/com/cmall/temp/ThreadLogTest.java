package com.cmall.temp;

import org.apache.log4j.Logger;

public class ThreadLogTest {
	static Logger logger = Logger.getLogger(ThreadLogTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		for (int i = 0; i < 5; i++) {
			new Thread(myThread, "mythread" + i).start();
		}
	}
}

class MyThread implements Runnable {

	public void run() {
		Logger logger = ThreadLoggerFactory.getLogger();
		logger.debug(Thread.currentThread().getName() + " -----debug");
		logger.info(Thread.currentThread().getName() + " -----info");
	}
}