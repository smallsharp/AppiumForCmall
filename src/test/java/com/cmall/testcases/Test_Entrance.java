package com.cmall.testcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.cmall.appium.DriverFactory;

/**
 * 测试多设备
 * 
 * @author cm
 *
 */
public class Test_Entrance {
	
	DriverFactory driverFactory = null;

	@BeforeSuite
	public void testMethod_two() {
		driverFactory = new DriverFactory();
		driverFactory.startServer_And_BuildDriver();
	}

	@Test
	public void testcase_01() {
		
		LoginTestCase loginTestCase = new LoginTestCase();
		
		System.out.println("Test");
		driverFactory.startTest(loginTestCase);
	}

	@AfterSuite
	public void teardown() {
		
		System.out.println("After");
	}

}
