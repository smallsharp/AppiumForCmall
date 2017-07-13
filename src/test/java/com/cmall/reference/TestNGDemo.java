package com.cmall.reference;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * TestNg 执行顺序
 * @author cm
 *
 */
public class TestNGDemo {

	@BeforeSuite
	public void beforesuite() {
		System.out.println("before suite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("after suite");
	}

	@BeforeTest
	public void btest() {
		System.out.println("before test");
	}

	@AfterTest
	public void aftertest() {
		System.out.println("after test");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("beforeclass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("afterclass");
	}

	@Test
	public void case1() {
		System.out.println("case 1");
	}

	@Test
	public void case2() {
		int num = 5;
		Assert.assertEquals(num, 5, "Result Error");
		System.out.println("case 2");
	}

	

}
