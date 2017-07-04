package com.cmall.test;

import java.util.List;
import java.util.Vector;
import org.testng.annotations.Test;
import com.cmall.appium.AppiumServer;
import com.cmall.appium.DDMlibUtil;
import com.cmall.http.LogUtil;
import com.cmall.testcases.LoginTestcase;
import com.cmall.testcases.TestCase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * 测试多设备 
 * 最原始的写法
 * @author cm
 *
 */
public class Multidevice {
	
	LogUtil log = new LogUtil(Multidevice.class);

	@Test
	public void test(){
		
	    Vector<Thread> threads = new Vector<Thread>();  
		List<String> list = DDMlibUtil.getDeviceNames();
		int port = 4723;
		TestCase testcase = new LoginTestcase();
		
		for (int i = 0; i < list.size(); i++) {
//			MyRunnable runnable = new MyRunnable(port, list.get(i));
			MyRunnable runnable = new MyRunnable(port, list.get(i), testcase);
			Thread thread = new Thread(runnable);
			threads.add(thread);
			thread.start();
			port += 2;
		}
		
		for (Thread iThread : threads) {
			try {
				iThread.join();// 等待所有子线程执行完毕
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		log.info("所有线程任务，全部执行完毕！");

	}
	
	AppiumServer appiumServer = new AppiumServer();
	
	class MyRunnable implements Runnable {
		
		private int port;
		private String deviceName;
		private TestCase testcase;
		private AndroidDriver<MobileElement> mdriver;

		public MyRunnable() {
		}
		
		public MyRunnable(TestCase testcase) {
			this.testcase = testcase;
		}
		
		public MyRunnable(int port, String deviceName) {
			this.port = port;
			this.deviceName = deviceName;
		}
		
		public MyRunnable(int port, String deviceName,TestCase testcase) {
			this.port = port;
			this.deviceName = deviceName;
			this.testcase = testcase;
		}

		@Override
		public void run() {
			try {
				log.info("run方法 on thread："+Thread.currentThread().getName());
				// 启动Appium服务器
				appiumServer.startServer(port, deviceName);
				Thread.sleep(1000);
				// 初始化driver
				mdriver = DriverFactory.initDriver(port, deviceName);
				testcase.setDriver(mdriver);
				// 执行测试用例
				testcase.runCase();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				log.info("run方法执行完毕："+Thread.currentThread().getName());
			}
		}
	}

}
