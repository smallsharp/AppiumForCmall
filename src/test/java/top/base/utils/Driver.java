package top.base.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import top.base.listener.MyAlertEventListener;
import top.base.listener.MyElementEventListener;

/**
 * 初始化driver
 * @author lee
 *
 */
public class Driver {

	private static AndroidDriver<WebElement> mdriver;

	public static AndroidDriver<WebElement> newInstance() {

		if (mdriver == null) {
			PropertyUtil pro = new PropertyUtil("/app.properties"); // 这里需要加个/表示类的根目录,从配置中取数据
			String deviceName = pro.getValue("deviceName_redmi");
			String appPackage = pro.getValue("appPackage");
			String appActivity = pro.getValue("appActivity");
			initAndroidDriver(deviceName, appPackage, appActivity);
		}
		return mdriver;
	}

	private static void initAndroidDriver(String deviceName, String appPackage, String appActivity) {

		Reporter.log("========== 正在准备测试环境，预计20s，请稍后 ==========", true);
		File classpathRoot = new File(System.getProperty("user.dir"));
		// 指定app的存放目录
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "play.apk");

		DesiredCapabilities dc = new DesiredCapabilities();
		// 支持中文输入
		dc.setCapability("unicodeKeyboard", "True");
		// 重置输入法
		dc.setCapability("resetKeyboard", "True");
		// 不需要再次安装
		// dc.setCapability("noReset", true);
		// 不对应用重签名
//		dc.setCapability("stopAppOnReset", false);
		dc.setCapability("noSign", "True");
		dc.setCapability("platformName", "Android");
		dc.setCapability("deviceName", deviceName);
		dc.setCapability("platformVersion", "5.0");
		dc.setCapability("appPackage", appPackage);
		dc.setCapability("appActivity", appActivity);
		dc.setCapability("app", app.getAbsolutePath());

		try {

			mdriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
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
