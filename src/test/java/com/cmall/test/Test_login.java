package com.cmall.test;

import org.testng.annotations.Test;
import com.cmall.appium.MultideviceManage;
import com.cmall.appium.MultideviceManage2;
import com.cmall.testcases.ITestCase;
import com.cmall.testcases.TestCase_login;

public class Test_login {
	
	// 利用MultideviceManage 测试多设备
	MultideviceManage m = new MultideviceManage();
	@Test
	public void testMultiDevice(){
		ITestCase loginTestCase = new TestCase_login();
		m.runTestCase(loginTestCase);
	}
	
	
	
	// 利用TestMulti 测试多设备，经测试AppiumServer不支持多设备，始终使用第一个设备
	@Test
	public void test2() {
		MultideviceManage2 m2 = new MultideviceManage2();
		ITestCase loginTestCase = new TestCase_login();
		m2.runTestCase(loginTestCase);
	}

}
