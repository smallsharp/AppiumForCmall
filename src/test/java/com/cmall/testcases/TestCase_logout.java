package com.cmall.testcases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import com.cmall.play.pages.PersonPage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TestCase_logout {
	
	AndroidDriver<MobileElement> mDriver;
	
	public void testLogout() {
		PersonPage personPage = new PersonPage(mDriver);
		PageFactory.initElements(new AppiumFieldDecorator(mDriver, 20 ,TimeUnit.SECONDS), personPage);
		personPage.logout();
	}
	
	public TestCase_logout() {
		// TODO Auto-generated constructor stub
	}
	
	public TestCase_logout(AndroidDriver<MobileElement> mDriver) {
		this.mDriver = mDriver;
	}

}
