package com.cmall.testcases;

import org.testng.annotations.Test;

import com.cmall.appium.TestManage;
import com.cmall.http.LogUtil;

public class Test_Logout {
	LogUtil log  = new LogUtil(Test_Logout.class);
	TestManage manage  = TestManage.getInstance();
	@Test
	public void testLogout() {
		log.info("testLogout");
		TestCase logoutCase = new LogoutTestcase();
		manage.startTest(logoutCase);
	}

}
