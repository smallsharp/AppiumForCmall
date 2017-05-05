package top.base.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import top.base.listener.MyAlertEventListener;
import top.base.listener.MyElementEventListener;

/**
 * Driver的初始化工具类
 * 
 * @author lee
 *
 */
public class Driver {

//	private static AndroidDriver<WebElement> driver;
	private static MyAndroidDriver<WebElement> mdriver;
	private static Logger logger = Logger.getLogger(Driver.class);

	/**
	 * init AndroidDriver
	 * 
	 * @return AndroidDriver driver
	 */
	public static MyAndroidDriver<WebElement> newInstance() {

		if (mdriver == null) {
			initAndroidDriver("06694a9b006097fb", "com.tude.android", ".activity.SplashActivity");
			if (mdriver == null) {
				logger.info("driver is null");
			}
		}

		return mdriver;
	}

	private static void initAndroidDriver(String deviceName, String appPackage, String appActivity) {

		logger.info("initAndroidDriver--->starting");
//		File classpathRoot = new File(System.getProperty("user.dir"));
//		File appDir = new File(classpathRoot, "apps/");
		File app = new File("apps", "play.apk");

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("unicodeKeyboard", "True");
		dc.setCapability("resetKeyboard", "True");
		dc.setCapability("platformName", "Android");
		dc.setCapability("deviceName", deviceName);
		dc.setCapability("platformVersion", "5.0");
		dc.setCapability("appPackage", appPackage);
		dc.setCapability("appActivity", appActivity);
		dc.setCapability("app", app.getAbsolutePath());

		try {
			mdriver = new MyAndroidDriver<WebElement>(new URL("http://192.168.101.201:4723/wd/hub"), dc);			
			//注册MyDriverListener监听事件
			mdriver = EventFiringWebDriverFactory.getEventFiringWebDriver(mdriver, new MyElementEventListener(),new MyAlertEventListener());

		} catch (MalformedURLException e) {
			logger.info("initAndroidDriver--->error");
			e.printStackTrace();
		}
		// 全局等待10秒
		mdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("initAndroidDriver--->finish");

	}

}
