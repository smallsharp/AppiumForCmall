package com.cmall.temp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class SimpleDemo {

	private AndroidDriver<MobileElement> driver;

	@Test
	public void testdemo() {
		
		System.out.println("测试开始");

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("browserName", "Browser");
		dc.setCapability("unicodeKeyboard", "True");
		dc.setCapability("resetKeyboard", "True");
		dc.setCapability("platformName", "Android");
		dc.setCapability("deviceName", "85GBBMA2353T");

		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723"+"/wd/hub"), dc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

/*		driver.findElementById("控件ID").click();

		driver.findElementById("控件ID").click();*/
		
		driver.findElement(By.id("com.android.browser:id/search_hint")).click();
		System.out.println("测试完成");
		
	}

}
