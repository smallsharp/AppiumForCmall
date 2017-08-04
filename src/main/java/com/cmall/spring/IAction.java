package com.cmall.spring;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public interface IAction {
	
	void setDriver(AndroidDriver<MobileElement> driver);
	
	
	void doTest();

}
