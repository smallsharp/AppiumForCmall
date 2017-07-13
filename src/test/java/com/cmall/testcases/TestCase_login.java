package com.cmall.testcases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import com.cmall.play.pages.LoginPage;
import com.cmall.utils.LogUtil;
import com.cmall.utils.PropertyUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TestCase_login implements ITestCase {
	
	LogUtil log = new LogUtil(TestCase_login.class);
	private AndroidDriver<MobileElement> mdriver;
	
	@Override
	public void runCase() {
		log.info("runCase:loginTestCase");
		LoginPage loginPage = new LoginPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS), loginPage);
		loginPage.login(PropertyUtil.getString("mobile"), PropertyUtil.getString("password"));
	}

	@Override
	public void setDriver(AndroidDriver<MobileElement> mdriver) {
		this.mdriver = mdriver;
	}

}
