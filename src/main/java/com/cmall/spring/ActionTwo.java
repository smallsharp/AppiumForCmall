package com.cmall.spring;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ActionTwo implements IAction {
	
	private AndroidDriver<MobileElement> driver;
	
	@Override
	public void setDriver(AndroidDriver<MobileElement> mdriver) {
		this.driver = mdriver;
	}

	@Override
	public void doTest(){
		System.out.println("-------第二个测试-------");
		System.out.println(driver.currentActivity());
	}
	
}
