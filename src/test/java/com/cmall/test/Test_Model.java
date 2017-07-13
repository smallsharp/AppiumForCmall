package com.cmall.test;

import org.testng.annotations.Test;
import com.cmall.appium.MultideviceManage;
import com.cmall.testcases.ITestCase;
import com.cmall.testcases.TestCase_model;

public class Test_Model {
	
	MultideviceManage m = new MultideviceManage();

	@Test
	public void testMultiDevice(){
		ITestCase modelcase = new TestCase_model();
		m.runTestCase(modelcase);
	}

}
