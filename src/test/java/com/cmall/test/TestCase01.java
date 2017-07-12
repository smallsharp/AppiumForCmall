package com.cmall.test;

import org.testng.annotations.Test;
import com.cmall.appium.MultideviceManage;
import com.cmall.testcases.ITestCase;
import com.cmall.testcases.TestCaseFactory;

public class TestCase01 {
	
	MultideviceManage m = new MultideviceManage();

	@Test
	public void testMultiDevice(){
//		TestCase testcase = new LoginTestcase();
//		m.runTestCase(testcase);
		ITestCase testcase = TestCaseFactory.getInstance("com.cmall.testcases.LoginTestcase");
		m.runTestCase(testcase);
	}

}
