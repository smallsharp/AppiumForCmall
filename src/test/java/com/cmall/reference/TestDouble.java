package com.cmall.reference;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.cmall.appium.AppiumServer;
import com.cmall.utils.DDMlibUtil;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * 测试多设备-原始版本
 * @author cm
 *
 */
public class TestDouble {

//	@Test
	public void testMethod_one()  {
		MyRunnable runnable1 = new MyRunnable(4723, "022TAS7N51009853");
		MyRunnable runnable2 = new MyRunnable(4725, "85GBBMA2353T");
		Thread thread1 = new Thread(runnable1);
		Thread thread2 = new Thread(runnable2);
		thread1.start();
		thread2.start();
//		runnable2.run(); // 直接调用run，是在主线程执行
		try {
			thread1.join();
			thread2.join();
		} catch (Exception e) {
		}
		System.out.println("执行完毕！");
	}
	
	@Test
	public void testMethod_two(){
		List<String> list = DDMlibUtil.getDeviceNames();
		int port = 4723;
		for (int i = 0; i < list.size(); i++) {
			new Thread(new MyRunnable(port, list.get(i))).start();
			port += 2;
		}
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("over!");
	}

	
	class MyRunnable implements Runnable {
		
		AppiumServer appiumServer = new AppiumServer();
		AndroidDriver<MobileElement> mDriver;
		int port;
		String udid;

		public MyRunnable() {
		}

		public MyRunnable(int port, String udid) {
			this.port = port;
			this.udid = udid;
		}

		@Override
		public void run() {
			try {
				System.out.println("执行:run "+Thread.currentThread().getName());
				appiumServer.startServer("",port, udid);
				Thread.sleep(2000);
				mDriver = initDriver(port,udid);
				testCase();
				System.out.println("run:ok " + Thread.currentThread().getName());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}
		
		public void testCase(){
			
			System.out.println("执行：点击1");
	        mDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  

			MobileElement m1 = mDriver.findElementById("com.play.android:id/btn_profile");
			m1.click();
			
			System.out.println("执行：点击2");
			MobileElement m_accout = mDriver.findElementById("com.play.android:id/tv_account");
			m_accout.click();
		}

	}

	private AndroidDriver<MobileElement> initDriver(int port, String deviceName) {
		
		System.out.println("执行：initDriver");
		AndroidDriver<MobileElement> mdriver = null;
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("unicodeKeyboard", "True"); // 支持中文输入
		dc.setCapability("resetKeyboard", "True"); // 重置输入法
		dc.setCapability("platformName", "Android");
		dc.setCapability("deviceName", deviceName);
		dc.setCapability("appPackage", "com.play.android");
		dc.setCapability("appActivity", "com.play.android.activity.SplashActivity");

		File classPath = new File(System.getProperty("user.dir"));
		File app = new File(classPath, "apps/play-debug.apk"); // 指定app的存放目录
		dc.setCapability("app", app.getAbsolutePath());

		try {
			mdriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:" + port + "/wd/hub"), dc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("initDriver：OK");
		return mdriver;
	}

}
