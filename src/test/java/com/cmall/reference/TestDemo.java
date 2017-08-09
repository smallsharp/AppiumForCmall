package com.cmall.reference;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

/**
 * a demo for "夜神模拟器" 相机
 * @author cm
 *
 */
public class TestDemo {

	AndroidDriver<MobileElement> mdriver = null;

	@BeforeClass
	public void initDriver() {
		
		Map<String, String> map = new HashMap<>();
		map.put("platformName", "Android");
		map.put("deviceName", "127.0.0.1:62001");
		map.put("appPackage", "com.android.camera");
		map.put("appActivity", "com.android.camera.Camera");
		DesiredCapabilities dct = new DesiredCapabilities(map);

/*		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", "Android");
		dc.setCapability("deviceName", "127.0.0.1:62001");
		dc.setCapability("appPackage", "com.android.camera");
		dc.setCapability("appActivity", "com.android.camera.Camera");*/

		try {
			mdriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), dct);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() throws InterruptedException {
		System.out.println("test：开始执行");
		Thread.sleep(2000);
		MobileElement shutter_button = mdriver.findElementById("com.android.camera:id/shutter_button");
		shutter_button.click();
		mdriver.pressKeyCode(AndroidKeyCode.HOME);
		Thread.sleep(2000);
		System.out.println("test: 执行完成");

	}

}
