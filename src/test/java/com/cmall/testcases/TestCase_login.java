package com.cmall.testcases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import com.cmall.play.pages.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TestCase_login {
	
	AndroidDriver<MobileElement> mDriver;
	
	public void testLogin(String mobile, String password) {
		LoginPage loginPage = new LoginPage(mDriver);
		PageFactory.initElements(new AppiumFieldDecorator(mDriver, 20 ,TimeUnit.SECONDS), loginPage);
		loginPage.login_help(mobile, password);
	}
	
	public TestCase_login() {
		// TODO Auto-generated constructor stub
	}
	
	public TestCase_login(AndroidDriver<MobileElement> mDriver) {
		this.mDriver = mDriver;
	}

}
