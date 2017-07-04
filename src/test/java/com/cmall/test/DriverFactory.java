package com.cmall.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.cmall.http.LogUtil;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class DriverFactory {
	
	private static LogUtil log = new LogUtil(DriverFactory.class);

	public static AndroidDriver<MobileElement> initDriver(int port, String deviceName) {

		log.info("initDriver on " + deviceName + " " + port);
		AndroidDriver<MobileElement> mdriver = null;

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("unicodeKeyboard", "True"); // 支持中文输入
		dc.setCapability("resetKeyboard", "True"); // 重置输入法
		dc.setCapability("platformName", "Android");
		dc.setCapability("noset", false);
		dc.setCapability("deviceName", deviceName);
		dc.setCapability("appPackage", "com.play.android");
		dc.setCapability("appActivity", "com.play.android.activity.SplashActivity");

		File classPath = new File(System.getProperty("user.dir"));
		File app = new File(classPath, "apps/play-debug.apk"); // 指定app的存放目录
		dc.setCapability("app", app.getAbsolutePath());

		try {
			mdriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:" + port + "/wd/hub"), dc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		log.info("initDriver ok on " + deviceName + " " + port);
		return mdriver;
	}

}
