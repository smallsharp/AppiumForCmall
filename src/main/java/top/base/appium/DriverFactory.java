package top.base.appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import top.base.utils.PropertyUtil;


public class DriverFactory {

	// 定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
	private static volatile DriverFactory dFactory;
	private static AndroidDriver<MobileElement> mdriver;

	private DriverFactory() {
		// TODO Auto-generated constructor stub
	}

	public static DriverFactory getInstance() {
		// 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
		if (dFactory == null) {
			// 同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
			synchronized (DriverFactory.class) {
				// 未初始化，则初始instance变量
				if (dFactory == null) {
					dFactory = new DriverFactory();
				}
			}
		}
		return dFactory;
	}

	public AndroidDriver<MobileElement> initAndroidDriver(URL url) {
		
		String deviceName = null;
		
		if (mdriver == null) {
			PropertyUtil pro = new PropertyUtil("/app.properties"); // 这里需要加个/表示类的根目录,从配置中取数据
/*			appPackage = pro.getValue("appPackage");
			appActivity = pro.getValue("appActivity");*/
			deviceName = pro.getValue("deviceName_meizu");
			
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
//			dc.setCapability("platformVersion", "5.0");
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

}
