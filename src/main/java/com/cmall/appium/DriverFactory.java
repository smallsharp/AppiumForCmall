package com.cmall.appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import com.cmall.testcases.TestCase;
import com.cmall.utils.PropertyUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;


/**
 * driver生产车间
 * @author cm
 *
 */
public class DriverFactory {

	private  AndroidDriver<MobileElement> mdriver;

	public DriverFactory() {
		// TODO Auto-generated constructor stub
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
			// 全局等待20秒
			mdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Reporter.log("========== 环境准备完毕，测试即将开始 ==========", true);
		}
		return mdriver;
	}
	
	/**
	 * 单一测试时使用
	 * @return AndroidDriver
	 */
	public AndroidDriver<MobileElement> initAndroidDriver() {
		System.out.println("初始化前driver："+mdriver);
		if (mdriver == null) {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("unicodeKeyboard", "True"); // 支持中文输入
			dc.setCapability("resetKeyboard", "True"); // 重置输入法
			dc.setCapability("platformName", "Android");
			dc.setCapability("deviceName", "022TAS7N51009853");
			dc.setCapability("appPackage", "com.play.android");
			dc.setCapability("appActivity", "com.play.android.activity.SplashActivity");
			
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "play-debug.apk"); // 指定app的存放目录
			dc.setCapability("app", app.getAbsolutePath());

			try {
				mdriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723"+"/wd/hub"), dc);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			// 注册MyDriverListener监听事件
			mdriver = EventFiringWebDriverFactory.getEventFiringWebDriver(mdriver, new MyAppiumListener());
			
//			mdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Reporter.log("========== 环境准备完毕，测试即将开始 ==========", true);
			System.out.println("初始化后driver:" + mdriver);
		}
		return mdriver;
	}
	
	
	public AndroidDriver<MobileElement> initMeituDriver() {
		
		String deviceName = null;
		
		if (mdriver == null) {
			PropertyUtil pro = new PropertyUtil("/app.properties"); // 这里需要加个/表示类的根目录,从配置中取数据
			deviceName = pro.getValue("deviceName_meizu");
			
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("unicodeKeyboard", "True"); // 支持中文输入
			dc.setCapability("resetKeyboard", "True"); // 重置输入法
			dc.setCapability("noReset", true); // 不需要再次安装
			dc.setCapability("platformName", "Android");
			dc.setCapability("deviceName", deviceName);
			dc.setCapability("appPackage", "com.meitu.wheecam");
			dc.setCapability("appActivity", "com.meitu.wheecam.ui.MainActivity");
			dc.setCapability("recreateChromeDriverSessions", true);
/*			
			 ChromeOptions options2 = new ChromeOptions();
             options2.setExperimentalOption("androidProcess", "WEBVIEW_com.meitu.wheecam");
             dc.setCapability(ChromeOptions.CAPABILITY, options2);*/
			
			File classPath = new File(System.getProperty("user.dir"));
			File app = new File(classPath, "apps/meiyin.apk"); // 指定app的存放目录
			dc.setCapability("app", app.getAbsolutePath());

			try {
				mdriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723"+"/wd/hub"), dc);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			// 注册MyDriverListener监听事件
			mdriver = EventFiringWebDriverFactory.getEventFiringWebDriver(mdriver, new MyAppiumListener());
						
			Reporter.log("========== 环境准备完毕，测试即将开始 ==========", true);
		}
		return mdriver;
	}
	
	/**
	 * 多设备初始化时使用
	 * @param port
	 * @param deviceName
	 * @return AndroidDriver
	 */
	public AndroidDriver<MobileElement> init_AndroidDriver(int port, String deviceName) {
		
		System.out.println(deviceName + " init AndroidDriver ");
		
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
		System.out.println(deviceName + " init Driver OK ");
		return mdriver;
	}
	
	
	List<AndroidDriver<MobileElement>> AndroidDriver_List = null;
	List<String> devicesList = null;
	
	/**
	 * 1.启动服务，并初始化driver
	 * 2.循环启动，这里未使用多线程
	 * 3.如果连接设备过多，可能等待初始化的时间会长
	 */
	public void startServer_And_BuildDriver(){
		
		devicesList = DDMlibUtil.getDeviceNames();
		if (devicesList.size() < 1) {
			System.out.println("没有设备连接！");
			return;
		}
		AndroidDriver_List = new ArrayList<AndroidDriver<MobileElement>>();
		int port = 4723;
		for (String driverName : devicesList) {
			AppiumServer appiumServer = new AppiumServer();
			appiumServer.startServer(port, driverName);
			mdriver = init_AndroidDriver(port,driverName);
			AndroidDriver_List.add(mdriver);
			port = port + 2;
		}
		System.out.println("服务启动成功！");
	}
	

	/**
	 * 开始多线程
	 */
	public void startTest(TestCase testCase) {
		
		Thread thread = null;
		
		for (int i = 0; i < AndroidDriver_List.size(); i++) {
			testCase.setDriver(AndroidDriver_List.get(i));
			MyRunnable runnable = new MyRunnable(AndroidDriver_List.get(i) , testCase);
			thread = new Thread(runnable);
			thread.start();// 自动调用 MyRunnable 中的run方法
		}
		
		try {
			thread.join(); // 等待所有子线程执行结束
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("startThread：执行完毕！");
	}
	

}
