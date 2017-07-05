package com.cmall.test;

import org.testng.annotations.Test;

import com.cmall.appium.MultideviceManage;
import com.cmall.testcases.LoginTestcase;
import com.cmall.testcases.LogoutTestcase;
import com.cmall.testcases.TestCase;

public class TestTestCase {
	
	MultideviceManage m = new MultideviceManage();

	@Test
	public void testMultiDevice(){
		TestCase testcase = new LoginTestcase();
		m.runTestCase(testcase);
	}
	
	@Test
	public void testMultiDevice2() {
		TestCase testcase = new LogoutTestcase();
		m.runTestCase(testcase);
	}

}
