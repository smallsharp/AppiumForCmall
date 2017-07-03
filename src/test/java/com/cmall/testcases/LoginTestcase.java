package com.cmall.testcases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;

import com.cmall.http.LogUtil;
import com.cmall.play.pages.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginTestcase implements TestCase {
	LogUtil log = new LogUtil(LoginTestcase.class);
	public AndroidDriver<MobileElement> mdriver;
	
	@Override
	public void runCase() {
		log.info("runCase:loginTestCase");
		LoginPage loginPage = new LoginPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS), loginPage);
		String mobile = "18521035133";
		String password = "111111";
		loginPage.login(mobile, password);
	}

	@Override
	public void setDriver(AndroidDriver<MobileElement> mdriver) {
		this.mdriver = mdriver;
	}
}
