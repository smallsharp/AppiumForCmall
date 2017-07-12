package com.cmall.testcases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public interface ITestCase {
	
	/**
	 * 执行用例
	 */
	public void runCase();
	
	
	/**
	 * 传入mdriver
	 * @param mdriver
	 */
	public void setDriver(AndroidDriver<MobileElement> mdriver);

}
