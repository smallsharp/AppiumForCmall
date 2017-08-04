package com.cmall.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
/**
 * 使用spring 测试
 * @author cm
 *
 */
public class TestMain {
	
	private ApplicationContext ac;
	
	@Test
	public void test() {
		ac = new ClassPathXmlApplicationContext("spring.xml");
		ac.getBean("action_one" , IAction.class).doTest();
	}
	
	private void runTest(IAction action) {
		
		action.doTest();
	}

}
