package top.play.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import top.base.appium.Helper;
import top.base.appium.Helper2;

/**
 * 个人页面
 * @author lee
 *
 */
public class PersonPage {
	
	private AndroidDriver<MobileElement> driver;
	private Helper2 helper;

	@FindBy(id = "com.play.android:id/btn_profile")
	private WebElement e_my;// 我的
	
	@FindBy(id = "com.play.android:id/btn_profile_setting")
	private WebElement e_profile_setting;// 个人设置
	
	@FindBy(id = "com.play.android:id/btn_address")
	private WebElement e_btn_address;// 收货地址
	
	@FindBy(id = "com.play.android:id/btn_log_out")
	private WebElement e_logout;// 退出登录
	
	@FindBy(id = "android:id/button1")
	private WebElement e_quit_yes;// 退出登录-确认

	
	public PersonPage() {
		if (helper == null) {
			helper = new Helper2(driver);
		}
	}
	
	public PersonPage(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
		helper = new Helper2(driver);
		System.out.println("P helper:"+helper);
	}

	/**
	 * 退出登录
	 */
	public void logout() {
		
		boolean result = false;

		if (Helper.waitActivity(Play_ActivityList.HOME_ACTIVITY)) {
			e_my.click();
			helper.swipeUp();
			e_logout.click();
			e_quit_yes.click();
			e_my.click();
			if (Helper.waitActivity(Play_ActivityList.LOGIN_ACTIVITY)) {
				result = true;
			}
		}
		
		assertTrue(result,"退出登录失败…");

	}
	
	
	public void add_Delivery_Address(){
		boolean result = false;

		if (Helper.waitActivity(Play_ActivityList.HOME_ACTIVITY)) {
			e_my.click();
			e_btn_address.click();
		}
		
		assertTrue(result,"添加收货地址失败");
	}

}
