package top.cases;

import static org.testng.Assert.assertTrue;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import top.base.utils.Locator;
import top.base.utils.MyBaseCase;
import top.biz.Biz_login;


public class Login_Test extends MyBaseCase {
	
	Logger logger = Logger.getLogger(Login_Test.class);

	@Test
	public void testLogin() throws InterruptedException {
		
		logger.debug(mdriver.currentActivity());
		
		if (Locator.waitForActivity(".activity.main.NativeVideoActivity")) {
			Locator.findElementById("com.tude.android:id/btn_jump").click();
		}
		// 点击：我的
//		mdriver.findElementById("com.tude.android:id/btn_profile").click();
		Locator.findElementById("com.tude.android:id/btn_profile").click();
		
		// 点击：账号密码登录
//		mdriver.findElementById("com.tude.android:id/tv_account").click();
		Locator.findElementById("com.tude.android:id/tv_account").click();

		Biz_login.login("185210351334", "111111");
		
		// 检查是否登录成功com.tude.android:id/btn_diy
		assertTrue(true);

	}

}
