package top.cases;

import org.testng.annotations.Test;

import top.base.utils.Assist;
import top.base.utils.MyBaseCase;


public class ProfileSetting_Test extends MyBaseCase{
	
	@Test
	public void testSetting() throws InterruptedException{
		
		if (Assist.waitForActivity(Constant.HOME_ACTIVITY)) {
			
			// 点击 我的
			Assist.findElementById("com.tude.android:id/btn_profile").click();
			// 点击 系统设置
			Assist.findElementById("com.tude.android:id/btn_profile_setting").click();
			// 点击 保存
			Assist.findElementById("com.tude.android:id/btn_save").click();
		} 
	
	}

}
