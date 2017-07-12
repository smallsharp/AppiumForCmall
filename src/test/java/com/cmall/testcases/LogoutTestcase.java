package com.cmall.testcases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import com.cmall.play.pages.PersonPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LogoutTestcase implements ITestCase{
	
	AndroidDriver<MobileElement> mdriver;

	@Override
	public void runCase() {
		// TODO Auto-generated method stub
		PersonPage personPage = new PersonPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS), personPage);
		personPage.logout();
		
	}

	@Override
	public void setDriver(AndroidDriver<MobileElement> mdriver) {
		// TODO Auto-generated method stub
		this.mdriver = mdriver;
		
	}
	


}
