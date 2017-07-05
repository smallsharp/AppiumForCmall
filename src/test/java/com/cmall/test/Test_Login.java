package com.cmall.test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.cmall.appium.TestManage2;
import com.cmall.http.LogUtil;
import com.cmall.testcases.LoginTestcase;
import com.cmall.testcases.TestCase;

/**
 * 尝试启动服务，和执行测试分离，结果不理想！
 * @author cm
 *
 */
public class Test_Login {
	
	LogUtil log = new LogUtil(Test_Login.class);
	TestManage2 manage = new TestManage2();
	
	@BeforeSuite
	public void before() {
		log.info("Before Suite");
		manage.startServer_And_BuildDriver();
	}
	
	@Test
	public void testlogin() {
		log.info("testlogin");
		TestCase loginCase  = new LoginTestcase();
		manage.startTest(loginCase);
	}
	
	@AfterSuite
	public void after() {
		log.info("After Suite");
//		manage.stopTest();
	}

}