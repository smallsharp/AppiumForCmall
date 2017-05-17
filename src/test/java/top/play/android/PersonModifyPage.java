package top.play.android;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import top.base.utils.Assist;

public class PersonModifyPage {
	
	@FindBy(id = "com.tude.android:id/btn_profile")
	private WebElement e_my;// 我的
	
	@FindBy(id = "com.tude.android:id/btn_profile_setting")
	private WebElement e_profile_setting;// 个人设置
	
	@FindBy(id="com.tude.android:id/rl_picture")
	private WebElement e_picture;// 个人头像
	
	@FindBy(id="com.tude.android:id/rl_nikeName")
	private WebElement e_nickName;// 昵称
	
	@FindBy(id="com.tude.android:id/tv_about_me")
	private WebElement e_aboutme;// 个性签名
	
	@FindBy(id="com.tude.android:id/tv_gender")
	private WebElement e_gender;// 个人设置-性别
	
	@FindBy(id="com.tude.android:id/tv_male")
	private WebElement e_male;// 性别：男
	
	@FindBy(id="com.tude.android:id/btn_save")
	private WebElement e_save;// 个人设置-保存
	
	
	
	/**
	 * 修改个人信息
	 */
	public void modifyPersonInfo(){
		boolean result = false;

		if (Assist.isActivityDisplayed(Constant.HOME_ACTIVITY)) {
			e_my.click();
			e_profile_setting.click();
			if (Assist.isActivityDisplayed(Constant.MODIFY_USER_INFO_ACTIVITY)) {
				e_gender.click();
				e_male.click();
				e_save.click();
				if (Assist.isActivityDisplayed(Constant.HOME_ACTIVITY)) {
					result = true;
				}
			}
		}
		
		assertTrue(result,"修改个人信息失败");
	}
	

}
