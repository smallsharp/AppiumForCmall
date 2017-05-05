package top.cases;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import top.base.utils.MyTestCase;
import top.base.utils.WaitUtils;
import top.biz.Biz_login;

/**
 * @Listeners({TestNGListener.class}),表示监听，可以同时监听多个；
 * testng.xml--<listeners></listeners>中配置,也可以
 * @author tester_lee
 *
 */

public class Case_login extends MyTestCase {
	
	Logger logger = Logger.getLogger(Case_login.class);

	@Test
	public void testLogin() throws InterruptedException {
		
		logger.debug(mdriver.currentActivity());
		
		if (WaitUtils.waitForActivity(".activity.main.NativeVideoActivity")) {
			WaitUtils.findElementById("com.tude.android:id/btn_jump").click();
		}
		// 点击：我的
//		mdriver.findElementById("com.tude.android:id/btn_profile").click();
		WaitUtils.findElementById("com.tude.android:id/btn_profile").click();
		
		// 点击：账号密码登录
//		mdriver.findElementById("com.tude.android:id/tv_account").click();
		WaitUtils.findElementById("com.tude.android:id/tv_account").click();

		Biz_login.login("185210351334", "111111");
		
		// 检查是否登录成功com.tude.android:id/btn_diy
		assertTrue(false);

	}

}
