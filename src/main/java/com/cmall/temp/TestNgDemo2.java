package com.cmall.temp;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestNgDemo2 {

	@BeforeClass
	public void beforeClass() {
		System.out.println("beforeclass:this is class B");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("afterclass:beforeclass:this is class B");
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
