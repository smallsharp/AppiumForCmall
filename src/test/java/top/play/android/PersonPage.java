package top.play.android;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import top.base.utils.Assist;

/**
 * 个人页面
 * @author lee
 *
 */
public class PersonPage {

	@FindBy(id = "com.tude.android:id/btn_profile")
	private WebElement e_my;// 我的
	
	@FindBy(id = "com.tude.android:id/btn_profile_setting")
	private WebElement e_profile_setting;// 个人设置
	
	@FindBy(id = "com.tude.android:id/btn_address")
	private WebElement e_btn_address;// 收货地址
	
	
	@FindBy(id = "com.tude.android:id/btn_log_out")
	private WebElement e_logout;// 退出登录
	
	@FindBy(name = "确认")
	private WebElement e_quit_yes;// 退出登录-确认

	/**
	 * 退出登录
	 */
	public void logout() {
		
		boolean result = false;

		if (Assist.isActivityDisplayed(Constant.HOME_ACTIVITY)) {
			e_my.click();
			Assist.swipeUpUntilFind("退出登录");
			e_logout.click();
			e_quit_yes.click();
			e_my.click();
			if (Assist.isActivityDisplayed(Constant.LOGIN_ACTIVITY)) {
				result = true;
			}
		}
		
		assertTrue(result,"退出登录失败…");

	}
	
	
	public void add_Delivery_Address(){
		boolean result = false;

		if (Assist.isActivityDisplayed(Constant.HOME_ACTIVITY)) {
			e_my.click();
			e_btn_address.click();
		}
		
		assertTrue(result,"添加收货地址失败");
	}

}
