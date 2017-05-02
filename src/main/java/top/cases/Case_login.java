package top.cases;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import top.baseutils.MyTestCase;
import top.baseutils.TestNGListener;
import top.biz.Biz_login;

/**
 * @Listeners({TestNGListener.class}),表示监听，可以同时监听多个；
 * testng.xml--<listeners></listeners>中配置,也可以
 * @author tester_lee
 *
 */
@Listeners(TestNGListener.class)
public class Case_login extends MyTestCase{
	
	@Test
	public void testMethod01(){		
		Biz_login.login(Constant.MOBILE_VALUE, Constant.CODE_VALUE_ERROR);
		boolean result = mdriver.findElementByName("验证码错误，请重新填写").isDisplayed();
		mdriver.findElementById("com.lifang.agent:id/ok_btn").click();
		assertTrue(result, "验证码输入错误，没有弹窗提示");
	}
	
	
	@Test
	public void testMethod02(){		
		Biz_login.login(Constant.MOBILE_VALUE2, Constant.CODE_VALUE_RIGHT);
		boolean result = mdriver.findElementByName("验证码错误，请重新填写").isDisplayed();
		mdriver.findElementById("com.lifang.agent:id/ok_btn").click();
		assertTrue(result, "验证码输入错误，没有弹窗提示");
	}
	
	
	
}
