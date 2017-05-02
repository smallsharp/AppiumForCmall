package top.testDemo;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;


public class TestDemo {

	@Test
	public void testMethodOne(){
		System.out.println("test------------>>> one");
		assertTrue(true);
	}
	
	@Test
	public void testMethodTwo(){
		System.out.println("test------------>>> two");
		assertTrue(false);

	}

}
