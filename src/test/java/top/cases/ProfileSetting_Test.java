package top.cases;

import org.testng.annotations.Test;

import top.base.utils.Locator;
import top.base.utils.MyBaseCase;


public class ProfileSetting_Test extends MyBaseCase{
	
	@Test
	public void testSetting() throws InterruptedException{
		
		Locator.findElementById("com.tude.android:id/btn_profile").click();
		
		Locator.findElementById("com.tude.android:id/btn_profile_setting").click();
		
		Locator.findElementById("com.tude.android:id/btn_save").click();
		
	}

}
