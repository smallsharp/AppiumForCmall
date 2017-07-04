package com.cmall.appium;

import com.cmall.http.LogUtil;
import com.cmall.testcases.TestCase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * 多线程操作
 * 需要并行的操作，写在run方法中
 * @author cm
 *
 */
public class MyRunnable implements Runnable {
	
	LogUtil log = new LogUtil(MyRunnable.class);
	AndroidDriver<MobileElement> mdriver;
	TestCase testCase;
	
	public MyRunnable() {
		
	}
	
	public MyRunnable(TestCase testCase) {
		this.testCase = testCase;
	}
	
	@Override
	public void run() {
		try {
			log.info("开始执行run方法："+ Thread.currentThread().getName());
			testCase.runCase();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			log.info("run 方法执行结束："+ Thread.currentThread().getName());
		}
	}
}