package top.base.utils;

import java.net.URL;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;

public class MyAndroidDriver<T> extends AndroidDriver<WebElement> {
	
	public MyAndroidDriver(URL remoteAddress, Capabilities desiredCapabilities) {
		super(remoteAddress, desiredCapabilities);
		// TODO Auto-generated constructor stub
	}

	public String getScreenshotAs(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
