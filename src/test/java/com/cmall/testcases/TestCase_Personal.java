package com.cmall.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cmall.appium.BaseCase;
import com.cmall.appium.TestNGListener;
import com.cmall.play.pages.PersonPage;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@Listeners(TestNGListener.class)
public class TestCase_Personal extends BaseCase{

	@Test(description = "退出登录")
	public void testLogout() {
		System.out.println("testLogout");
		PersonPage personPage = new PersonPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS), personPage);
		personPage.logout();
	}

}
