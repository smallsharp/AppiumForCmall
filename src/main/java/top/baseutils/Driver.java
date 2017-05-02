package top.baseutils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.appium.java_client.events.EventFiringWebDriverFactory;

public class Driver {

//	private static AndroidDriver<WebElement> driver;
	private static MyAndroidDriver<WebElement> mdriver;
	private static Logger logger = Logger.getLogger(Driver.class);

	/**
	 * 初始化driver，返回driver实例
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

		logger.info("initAndroidDriver------> START");

/*		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps/");
		File app = new File("apps", "YFYK2_3.0_Test.apk");*/

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("unicodeKeyboard", "True");
		dc.setCapability("resetKeyboard", "True");
		dc.setCapability("platformName", "Android");
		dc.setCapability("deviceName", deviceName);
		dc.setCapability("platformVersion", "5.0");
		dc.setCapability("appPackage", appPackage);
		dc.setCapability("appActivity", appActivity);
//		dc.setCapability("app", app.getAbsolutePath());

		try {
			mdriver = new MyAndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);			
			EventFiringWebDriverFactory.getEventFiringWebDriver(mdriver, new MyDriverListener());

		} catch (MalformedURLException e) {
			logger.info("initAndroidDriver------> ERROR");
			e.printStackTrace();
		}
		// 全局等待10秒
		mdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("initAndroidDriver------> SUCCESS");

	}

}
