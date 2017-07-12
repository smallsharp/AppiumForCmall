package com.cmall.testcases;

import com.cmall.appium.MultideviceManage;

/**
 * 反射工厂类
 * 
 * @author cm
 *
 */
public class TestCaseFactory {

	public static ITestCase getInstance(String ClassName) {
		
		ITestCase testcase = null;
		
		try {
			testcase = (ITestCase) Class.forName(ClassName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testcase;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ITestCase testcase = TestCaseFactory.getInstance("com.cmall.testcases.LoginTestcase");
		MultideviceManage m = new MultideviceManage();
		m.runTestCase(testcase);
	}

}
