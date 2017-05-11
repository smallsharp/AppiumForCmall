package top.base.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import top.base.listener.MyAlertEventListener;
import top.base.listener.MyElementEventListener;

/**
 * 初始化driver
 * 
 * @author Administrator
 *
 */
public class Driver {

	// private static AndroidDriver<WebElement> driver;
	private static AndroidDriver<WebElement> mdriver;

	public static AndroidDriver<WebElement> newInstance() {

		if (mdriver == null) {
			/*
			 * String deviceName = "85GBBMA2353T"; String appPackage =
			 * "com.tude.android"; String appActivity =
			 * ".activity.SplashActivity";
			 */

			PropertyUtil pro = new PropertyUtil("/app.properties"); // 这里需要加个/表示类的根目录,从配置中取数据
			String deviceName = pro.getValue("deviceName_huawei");
			String appPackage = "com.tude.android";
			String appActivity = ".activity.SplashActivity";
			initAndroidDriver(deviceName, appPackage, appActivity);
		}
		return mdriver;
	}

	private static void initAndroidDriver(String deviceName, String appPackage, String appActivity) {

		Reporter.log("========== 正在准备测试环境，请稍后 ==========", true);
		File classpathRoot = new File(System.getProperty("user.dir"));
		// 指定app的存放目录
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "play.apk");

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		// 支持中文输入
		dc.setCapability("unicodeKeyboard", "True");
		// 重置输入法
		dc.setCapability("resetKeyboard", "True");
		// 不需要再次安装
		// dc.setCapability("noReset", true);
//		dc.setCapability("fullReset", "True");
        dc.setCapability("autoAcceptAlerts", true);// 自动接受提示信息
		// 不对应用重签名
		dc.setCapability("noSign", "True");
		dc.setCapability("platformName", "Android");
		dc.setCapability("deviceName", deviceName);
		dc.setCapability("platformVersion", "5.0");
		dc.setCapability("appPackage", appPackage);
		dc.setCapability("appActivity", appActivity);
		dc.setCapability("app", app.getAbsolutePath());

		try {

			mdriver = new AndroidDriver<WebElement>(new URL("http://192.168.101.201:4723/wd/hub"), dc);
			// 注册MyDriverListener监听事件
			mdriver = EventFiringWebDriverFactory.getEventFiringWebDriver(mdriver, new MyElementEventListener(),
					new MyAlertEventListener());

		} catch (MalformedURLException e) {
			Reporter.log("========== 测试环境启动失败，请重试 ==========", true);
			e.printStackTrace();
		}
		// 全局等待10秒
		mdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Reporter.log("========== 环境准备完毕，测试即将开始 ==========", true);
	}

}
