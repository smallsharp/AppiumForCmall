package top.tude.android;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import top.base.appium.Helper;
import top.play.android.ActivityList;

/**
 * PO模式
 * @author lee
 *
 */
public class LoginPage {
	// 跳过
	@FindBy(id = "com.tude.android:id/btn_jump")
	private WebElement e_skipVideo;
	// 我的
	@FindBy(id = "com.tude.android:id/btn_profile")
	private WebElement e_my;
	// 账号密码登录
	@FindBy(id = "com.tude.android:id/tv_account")
	private WebElement e_account;
	// 手机号
	@FindBy(id = "com.tude.android:id/et_account")
	private WebElement e_molibe;
	// 密码
	@FindBy(id = "com.tude.android:id/et_password")
	private WebElement e_password;
	// 登录
	@FindBy(id = "com.tude.android:id/btn_login")
	private WebElement e_login;
	
	/**
	 * 通过：手机号，密码登录
	 * @param mobile
	 * @param password
	 */
	public void login(String mobile, String password) {

		boolean result = false;

		if (Helper.isActivityDisplayed(ActivityList.VIDEO_ACTIVITY)) {
			e_skipVideo.click();
		}

		if (Helper.isActivityDisplayed(ActivityList.HOME_ACTIVITY)) {
			e_my.click();
			if (Helper.isActivityDisplayed(ActivityList.LOGIN_ACTIVITY)) {
				// 点击：账号密码登录
				e_account.click();
				e_molibe.sendKeys(mobile);
				e_password.sendKeys(password);
				e_login.click();
				// 登录成功Activity：LOGIN_ACTIVITY-->HOME_ACTIVITY
				if (Helper.isActivityDisplayed(ActivityList.HOME_ACTIVITY)) {
					result = true;
				}
			}
		}

		assertTrue(result);

	}
}
