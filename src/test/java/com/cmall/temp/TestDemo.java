package com.cmall.temp;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * a demo for "夜神模拟器" 相机
 * @author cm
 *
 */
public class TestDemo {

	AndroidDriver<MobileElement> mdriver = null;

	@BeforeClass
	public void initDriver() {

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", "Android");
		dc.setCapability("deviceName", "127.0.0.1:62001");
		dc.setCapability("appPackage", "com.android.camera");
		dc.setCapability("appActivity", "com.android.camera.Camera");

		try {
			mdriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() throws InterruptedException {
		System.out.println("test：开始执行");
		Thread.sleep(2000);
		MobileElement btn = mdriver.findElementById("com.android.camera:id/shutter_button");
		btn.click();
		Thread.sleep(2000);
		System.out.println("test: 执行完成");

	}

}
