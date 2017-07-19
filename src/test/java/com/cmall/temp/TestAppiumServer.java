package com.cmall.temp;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cmall.appium.AppiumServerUtil;
import com.cmall.appium.DriverProduce;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TestAppiumServer {
	int port = 4723;
	String ip = "127.0.0.1";
	AndroidDriver<MobileElement> mdriver = null;
	AppiumServerUtil server = new AppiumServerUtil();
	@BeforeClass
	public void setup() {
		server.startServer(ip, port,"80c20e99");
		mdriver = DriverProduce.initDriver(ip, port, "80c20e99");//80c20e99   85GBBMA2353T
	}
	
	
	@Test
	public void test() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println(mdriver.currentActivity());
	}
	
	@BeforeClass
	public void teardown() {
		mdriver.quit();
		server.stopServer();
	}

}
