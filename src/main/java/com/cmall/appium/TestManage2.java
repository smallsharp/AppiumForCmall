package com.cmall.appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.openqa.selenium.remote.DesiredCapabilities;
import com.cmall.http.LogUtil;
import com.cmall.testcases.TestCase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;

/**
 * 单例模式：测试服务管理类
 * @author cm
 *
 */
public class TestManage2 {
	
	private LogUtil log = new LogUtil(TestManage2.class);
	
	public TestManage2() {
		System.out.println("a new manage!");
	}
	
	private  AndroidDriver<MobileElement> mdriver;
	private AppiumServer appiumServer = new AppiumServer();

	/**
	 * 适用于多设备初始化
	 * @param port
	 * @param deviceName
	 * @return AndroidDriver
	 */
	public AndroidDriver<MobileElement> initAndroidDriver(int port, String deviceName) {
		
		log.info("init AndroidDriver with:" + deviceName + " "+ port);
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
		log.info("init AndroidDriver OK with:" + deviceName);
		
		return mdriver;
	}
	
	List<AndroidDriver<MobileElement>> driverList = null;
	
	/**
	 * 1.启动服务，并初始化driver
	 * 2.循环启动，这里未使用多线程
	 * 3.如果连接设备过多，可能等待初始化的时间会长
	 */
	public void startServer_And_BuildDriver(){
		log.info("开始执行：startServer_And_BuildDriver() ");
		
		List<String> devicesList = DDMlibUtil.getDeviceNames();
		if (devicesList.size() < 1) {
			log.error("没有发现设备连接，请重新插拔手机设备后，重试！");
			return;
		}
		driverList = new ArrayList<AndroidDriver<MobileElement>>();
		int port = 4723;
		for (String driverName : devicesList) {
			
			appiumServer.startServer(port, driverName);
			log.info("服务启动成功："+ driverName + " " + port);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mdriver = initAndroidDriver(port,driverName);
			driverList.add(mdriver);
			port = port + 2;
		}
		log.info("所有启动服务成功，共初始化设备："+ devicesList.size()+"台。");
	}
	

	/**
	 * 开始多线程,执行MyRunnable中的run方法
	 */
	public void startTest(TestCase testCase) {
		
		log.info("开始执行：startTest ");
	    Vector<Thread> threads = new Vector<Thread>();  

		for (int i = 0; i < driverList.size(); i++) {
			MyRunnable runnable = new MyRunnable(testCase);
			Thread thread = new Thread(runnable);
			threads.add(thread);
			testCase.setDriver(driverList.get(i));
			thread.start();// 自动调用 MyRunnable 中的run方法
		}
		
		for (Thread iThread : threads) {
			try {
				iThread.join();// 等待所有子线程执行完毕
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		log.info("startTest：执行完毕！");
	}
	
	/**
	 * 停止测试
	 */
	public void stopTest() {
		appiumServer.stopServer();
	}
	
	
	/**
	 * 提供给启动自带服务使用
	 * @param url
	 * @return
	 */
	public AndroidDriver<MobileElement> initAndroidDriver(URL url) {
		String deviceName = null;
		if (mdriver == null) {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "play-debug.apk"); // 指定app的存放目录
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("unicodeKeyboard", "True"); // 支持中文输入
			dc.setCapability("resetKeyboard", "True"); // 重置输入法
			dc.setCapability("browserName", "");
//			dc.setCapability("noReset", true); // 不需要再次安装
			dc.setCapability("noSign", "True");
			dc.setCapability("platformName", "Android");
			dc.setCapability("deviceName", deviceName);
			dc.setCapability("appPackage", "com.play.android");
			dc.setCapability("appActivity", "com.play.android.activity.SplashActivity");
			dc.setCapability("app", app.getAbsolutePath());
			mdriver = new AndroidDriver<MobileElement>(url, dc);
			// 注册MyDriverListener监听事件
			mdriver = EventFiringWebDriverFactory.getEventFiringWebDriver(mdriver, new MyAppiumListener());
//			mdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		return mdriver;
	}

}
