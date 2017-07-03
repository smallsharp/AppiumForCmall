package com.cmall.testcases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public interface TestCase {
	
	public void runCase();
	
	public void setDriver(AndroidDriver<MobileElement> mdriver);

}
