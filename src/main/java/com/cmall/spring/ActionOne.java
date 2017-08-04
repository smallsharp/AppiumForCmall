package com.cmall.spring;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class ActionOne implements IAction , IAction2 {
	
	private AndroidDriver<MobileElement> driver;
	
	@Override
	public void setDriver(AndroidDriver<MobileElement> mdriver) {
		this.driver = mdriver;
	}

	@Override
	public void doTest() {
		System.out.println("-------开始测试-------");
		try {
			Thread.sleep(1000);
			MobileElement btn = driver.findElementById("com.play.android:id/btn_profile");
			btn.click();
			Thread.sleep(1000);
			driver.pressKeyCode(AndroidKeyCode.HOME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------结束测试-------");
	}
	
	


	public void doTest2() {
		
		
	}
	
}
