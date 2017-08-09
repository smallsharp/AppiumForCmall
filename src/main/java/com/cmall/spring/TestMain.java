package com.cmall.spring;

import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import com.cmall.testcases.ITestCase;
import com.cmall.utils.DDMlibUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
/**
 * 使用spring 测试
 * @author cm
 *
 */
public class TestMain {
	
	private ApplicationContext ac;
	private Logger log = Logger.getLogger(TestMain.class);
	
	public void test() {
		ac = new ClassPathXmlApplicationContext("spring.xml");
		IActionOne one = (ActionOne) ac.getBean("action_one");
		one.login();
		one.logout();
 	}
	
	
	List<String> list = DDMlibUtil.getSerialNumber();
	
	AppiumServer appiumServer = new AppiumServer();

	@Test
	public void runTestCase() {
		Vector<Thread> threads = new Vector<Thread>();
		log.info("一共检测到设备：" + list.size() + "台");
		for (int i = 0; i < list.size(); i++) {
			log.info("第" + (i + 1) + "设备名称：" + list.get(i));
		}
		int port = 4723;
		
		for (int i = 0; i < list.size(); i++) {
			MyRunnable runnable = new MyRunnable(port, list.get(i), null);
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
	
	

	class MyRunnable implements Runnable {
		private int port;
		private String deviceName;
		private String ip;
		private ITestCase testcase;
		private AndroidDriver<MobileElement> mdriver;
		
		public MyRunnable() {
		}

		public MyRunnable(int port, String deviceName, ITestCase testcase) {
			this.port = port;
			this.deviceName = deviceName;
			this.testcase = testcase;
		}

		
		@Override
		public void run() {
			try {
				ip = "127.0.0.1";
				appiumServer.startServer(ip, port, deviceName);
				System.out.println("SSSSSSSSSSSSSSSSSS");
				Thread.sleep(3000);
				// 初始化driver
				mdriver = DriverFactory.initDriver(ip, port, deviceName);
				System.out.println("TTTTTTTTTTTTTTTTTTTTTT");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				appiumServer.stopServer();
				log.info("执行结束：" + deviceName);
			}
		}
		
	}

}
