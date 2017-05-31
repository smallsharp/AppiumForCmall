package top.temp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;


public class SimpleDemo {

	private AndroidDriver<WebElement> driver;

	@Test
	@Parameters({ "deviceName", "udid", "port" })
	public void setup(String deviceName, String udid, String port) {
		
		System.out.println(deviceName+" init...");

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("unicodeKeyboard", "True");
		dc.setCapability("resetKeyboard", "True");
		dc.setCapability("platformName", "Android");
		dc.setCapability("deviceName", deviceName);
		dc.setCapability("udid", deviceName);
		dc.setCapability("appPackage", "com.tude.android");
		dc.setCapability("appActivity", "com.tude.android.activity.SplashActivity");
		try {
			driver = new AndroidDriver<WebElement>(new URL("http://192.168.101.201:" + port + "/wd/hub"), dc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		System.out.println(deviceName + " init ok...");

		driver.findElementById("com.tude.android:id/btn_jump").click();

		driver.findElementById("com.tude.android:id/btn_profile").click();
		
		System.out.println(deviceName + " 测试完成");
	}

}
