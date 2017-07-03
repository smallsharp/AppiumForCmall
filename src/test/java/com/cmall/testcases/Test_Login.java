package com.cmall.testcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.cmall.appium.TestManage;
import com.cmall.http.LogUtil;

/**
 * 测试多设备
 * @author cm
 *
 */
public class Test_Login {
	
	LogUtil log = new LogUtil(Test_Login.class);
	TestManage manage = TestManage.getInstance();
	@Test
	public void testlogin() {
		log.info("testlogin");
		TestCase loginCase  = new LoginTestcase();
		manage.startTest(loginCase);
	}
	
	@BeforeSuite
	public void before() {
		log.info("Before Suite");
		manage.startServer_And_BuildDriver();
	}
	
	@AfterSuite
	public void after() {
		log.info("After Suite");
	}

}
