package com.cmall.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import com.cmall.appium.MultideviceManage;
import com.cmall.testcases.ITestCase;
import com.cmall.testcases.TestCase_model;

public class Main {
	

	@Test
	public void testMultiDevice(){
		MultideviceManage m = new MultideviceManage();
		ITestCase modelcase = new TestCase_model();
		m.runTestCase(modelcase);
	}
	
	@Test(description="使用spring")
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		MultideviceManage m = (MultideviceManage) ac.getBean("multidevice");
		ITestCase modelcase = (ITestCase) ac.getBean("testcase_model");
		m.runTestCase(modelcase);
	}

}
