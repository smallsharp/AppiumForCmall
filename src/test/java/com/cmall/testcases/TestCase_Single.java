package com.cmall.testcases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.cmall.appium.TestManage2;
import com.cmall.appium.TestNGListener;
import com.cmall.play.pages.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@Listeners(TestNGListener.class)
public class TestCase_Single{
    static AndroidDriver<MobileElement> mdriver = null;
	TestManage2 manage = null;
	
	@BeforeSuite
	public void setup() {
		System.out.println("Before");
		manage = new TestManage2();
		mdriver= manage.initAndroidDriver(4723, "022TAS7N51009853");
	}

	@Test(description = "退出登录")
	public void testLogout() {
		System.out.println("Test");
		LoginPage login = new LoginPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20, TimeUnit.SECONDS), login);
		login.login("18521035133", "111111");
	}
	
	@AfterSuite
	public void teardown() {
		System.out.println("After");
		mdriver.quit();
	}

}
