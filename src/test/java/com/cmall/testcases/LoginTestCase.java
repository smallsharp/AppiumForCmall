package com.cmall.testcases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LoginTestCase implements TestCase {
	
	public AndroidDriver<MobileElement> mdriver;
	
	@Override
	public void exe() {
		System.out.println("login");
		
		TestCase_login login = new TestCase_login(mdriver);
		login.testLogin("", "");
		TestCase_logout logout = new TestCase_logout(mdriver);
		logout.testLogout();

	}


	@Override
	public void setDriver(AndroidDriver<MobileElement> mdriver) {
		this.mdriver = mdriver;
		
	}
	
	

}
