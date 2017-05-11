package top.cases;

import org.testng.annotations.Test;

import top.base.utils.Locator;
import top.base.utils.MyBaseCase;


public class ProfileSetting_Test extends MyBaseCase{
	
	@Test
	public void testSetting() throws InterruptedException{
		
		if (Locator.waitForActivity(Constant.HOME_ACTIVITY)) {
			
			// 点击 我的
			Locator.findElementById("com.tude.android:id/btn_profile").click();
			// 点击 系统设置
			Locator.findElementById("com.tude.android:id/btn_profile_setting").click();
			// 点击 保存
			Locator.findElementById("com.tude.android:id/btn_save").click();
		} 
	
	}

}
