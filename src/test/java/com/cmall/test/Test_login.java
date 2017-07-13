package com.cmall.test;

import org.testng.annotations.Test;
import com.cmall.appium.MultideviceManage;
import com.cmall.testcases.ITestCase;
import com.cmall.testcases.TestCase_login;

public class Test_login {
	
	MultideviceManage m = new MultideviceManage();

	@Test
	public void testMultiDevice(){
		ITestCase loginTestCase = new TestCase_login();
//		ITestCase testcase = TestCaseFactory.getInstance("com.cmall.testcases.LoginTestcase");
		m.runTestCase(loginTestCase);
	}

}
