package com.cmall.testcases;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.cmall.appium.AppiumServer;
import com.cmall.appium.DDMlibUtil;
import com.cmall.play.pages.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * 测试多设备 
 * 最原始的写法
 * @author cm
 *
 */
public class Multidevice {

	@Test
	public void testMethod_two(){
		List<String> list = DDMlibUtil.getDeviceNames();
		int port = 4723;
		Thread thread = null;
		for (int i = 0; i < list.size(); i++) {
			MyRunnable runnable = new MyRunnable(port, list.get(i));
			thread = new Thread(runnable);
			thread.start();
			port += 2;
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("全部执行完毕");
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
				// 启动Appium服务器
				appiumServer.startServer(port, udid);
				// 初始化driver
				mDriver = initDriver(port,udid);
				// 执行测试用例
				String mobile = "18521035133";
				String password = "111111";
				testLogin(mobile,password);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("run:ok " + Thread.currentThread().getName());
			}
		}
		
		public void testLogin(String mobile, String password) {
			LoginPage loginPage = new LoginPage(mDriver);
			PageFactory.initElements(new AppiumFieldDecorator(mDriver, 20 ,TimeUnit.SECONDS), loginPage);
			loginPage.login_help(mobile, password);
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
			mdriver = new AndroidDriver<MobileElement>(new URL("http://192.168.101.227:" + port + "/wd/hub"), dc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("initDriver：OK");
		return mdriver;
	}

}
