package top.cases;

import static org.testng.Assert.assertTrue;
import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import top.base.listener.TestNGListener;
import top.base.utils.Assist;
import top.base.utils.MyBaseCase;
import top.biz.Biz_login;


@Listeners({TestNGListener.class})
public class Login_Test extends MyBaseCase {
	
	Logger logger = Logger.getLogger(Login_Test.class);	
	
	@Parameters({"mobile","pwd"})
	@Test
	public void testLogin(String moblie ,String pwd) throws InterruptedException {
				
		if (Assist.waitForActivity(".activity.main.NativeVideoActivity")) {
			Assist.findElementById("com.tude.android:id/btn_jump").click();
		}
		// 点击：我的
		Assist.findElementById("com.tude.android:id/btn_profile").click();
		
		boolean result = false;

		if (Assist.waitForActivity("com.tude.android.activity.member.LoginActivity")) {
			// 点击：账号密码登录
			Assist.findElementById("com.tude.android:id/tv_account").click();

			Biz_login.login(moblie, pwd);
			
			// 如果仍在当前页面，则说明登录失败
			if (Assist.waitForActivity("com.tude.android.activity.member.LoginActivity")) {
				result = false;
			}
			// 页面跳转，则登录成功
			if (Assist.waitForActivity("com.tude.android.activity.main.HomeActivity")) {
				result = true;
			}
		}
		
		assertTrue(result);

	}
	
	
	public void testLogout() throws InterruptedException{
		if (Assist.waitForActivity(Constant.HOME_ACTIVITY)) {
			Assist.findElementById("com.tude.android:id/btn_profile").click();
			

		}
	}

}
