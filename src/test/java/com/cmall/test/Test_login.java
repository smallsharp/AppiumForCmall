package com.cmall.test;

import org.testng.annotations.Test;
import com.cmall.appium.MultideviceManage;
import com.cmall.testcases.ITestCase;
import com.cmall.testcases.TestCase_login;

public class Test_login {
	
	// 利用MultideviceManage 测试多设备
	MultideviceManage m = new MultideviceManage();
	@Test
	public void testMultiDevice(){
		ITestCase loginTestCase = new TestCase_login();
//		ITestCase testcase = TestCaseFactory.getInstance("com.cmall.testcases.LoginTestcase");
		m.runTestCase(loginTestCase);
	}
	
	
	
	// 利用TestMulti 测试多设备，经测试AppiumServer不支持多设备，始终使用第一个设备
/*	TestMulti mu = new TestMulti();
	@Test
	public void test2() {
		ITestCase loginTestCase = new TestCase_login();
		mu.runTestCase(loginTestCase);
	}*/

}
