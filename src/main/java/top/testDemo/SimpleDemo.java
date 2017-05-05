package top.testDemo;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;

/**
 * 一个完整的例子，只做参考
 * @author Administrator
 *
 */

public class SimpleDemo {
	
	private static AndroidDriver<WebElement> driver;
	
	@BeforeClass
	public void setup() {

		System.out.println("****测试开始，正在初始化数据****");
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps/");
		File app = new File(appDir, "YFYK2_3.0_Test.apk");
		if (!app.exists()) {
			System.out.println("apk不存在");
		}

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("unicodeKeyboard", "True");
		dc.setCapability("resetKeyboard", "True");
		dc.setCapability("platformName", "Android");
		dc.setCapability("deviceName", "85GBBMA2353T");
//		dc.setCapability("platformVersion", "6.0.1");
		dc.setCapability("app", app.getAbsolutePath());
		try {
			driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("初始化完成...");
		
	}

	
	@Test
	public void testLogin() throws InterruptedException {
		System.out.println("Test------------->:ok");
		WebElement element = driver.findElementById("com.lifang.agent:id/get_verify_tv");
		for (int i = 0; i < 10; i++) {
			element.click();
			Thread.sleep(1000);
		}
		System.out.println("Test------------->:ok");

	}
	
	@AfterClass
	public void teardown() {
		System.out.println("****测试结束，正在清理数据****");
		driver.quit();
	}
	
}
