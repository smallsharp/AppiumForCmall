package com.cmall.appium;

import org.apache.log4j.Logger;

import com.cmall.testcases.TestCase;
import com.cmall.testcases.TestCase_login;
import com.cmall.testcases.TestCase_logout;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * 多线程操作
 * 需要并行的操作，写在run方法中
 * @author cm
 *
 */
public class MyRunnable implements Runnable {
	
	Logger log = Logger.getLogger(MyRunnable.class);
	AppiumServer appiumServer = new AppiumServer();
	
	int port;
	String deviceName;
	AndroidDriver<MobileElement> mDriver;
	
	TestCase testCase;
	
	public MyRunnable() {
		
	}
	
	public MyRunnable(AndroidDriver<MobileElement> mDriver , TestCase testCase) {
		this.mDriver = mDriver;
		this.testCase = testCase;
	}
	
	public MyRunnable(AndroidDriver<MobileElement> mDriver) {
		this.mDriver = mDriver;
	}

	public MyRunnable(int port, String deviceName) {
		this.port = port;
		this.deviceName = deviceName;
	}
	String mobile = "18521035133";
	String password = "111111";
	@Override
	public void run() {
		try {
			log.info("开始执行run方法："+Thread.currentThread().getName());
	/*		TestCase_login login = new TestCase_login(mDriver);
			login.testLogin(mobile, password);
			TestCase_logout logout = new TestCase_logout(mDriver);*/
			//logout.testLogout();
//			testCase.setDriver(mDriver);
			testCase.exe();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			log.info("run 方法执行结束："+Thread.currentThread().getName());
		}
	}
}