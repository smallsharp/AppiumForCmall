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

	public AndroidDriver<MobileElement> initAndroidDriver() {

		if (mdriver == null) {
			PropertyUtil pro = new PropertyUtil("/app.properties"); // 这里需要加个/表示类的根目录,从配置中取数据
			String udid1 = pro.getValue("deviceName_meizu");
			String appPackage = pro.getValue("appPackage");
			String appActivity = pro.getValue("appActivity");
			init(udid1, udid1, "4723", appPackage, appActivity);
		}
		return mdriver;
	}

	private void init(String deviceName, String udid, String port, String appPackage, String appActivity) {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "play.apk"); // 指定app的存放目录

		DesiredCapabilities dc1 = new DesiredCapabilities();
		dc1.setCapability("unicodeKeyboard", "True"); // 支持中文输入
		dc1.setCapability("resetKeyboard", "True"); // 重置输入法
		// dc.setCapability("noReset", true); // 不需要再次安装
		dc1.setCapability("noSign", "True");
		dc1.setCapability("platformName", "Android");
		dc1.setCapability("deviceName", deviceName);
		dc1.setCapability("udid", udid);
		dc1.setCapability("platformVersion", "5.0");
		dc1.setCapability("appPackage", appPackage);
		dc1.setCapability("appActivity", appActivity);
		dc1.setCapability("app", app.getAbsolutePath());

//		URL url = PlaySuite.getUrl();
		try {
			mdriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:"+port+"/wd/hub"), dc1);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 注册MyDriverListener监听事件
		mdriver = EventFiringWebDriverFactory.getEventFiringWebDriver(mdriver, new MyElementEventListener());
		// 全局等待20秒
		mdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("========== 环境准备完毕，测试即将开始 ==========", true);
	}

}
