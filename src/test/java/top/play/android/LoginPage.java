package top.play.android;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import top.base.utils.Assist;

/**
 * PO模式
 * 
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
	 * 
	 * @param mobile
	 * @param password
	 */
	public void login(String mobile, String password) {
		// 如果页面是在视频页面，则点击"跳过"
		if (Assist.isActivityDisplayed(Constant.VIDEO_ACTIVITY)) {
			e_skipVideo.click();
		}
		// 如果当前页面不在HOME_ACTIVITY，则测试失败
		if (!Assist.isActivityDisplayed(Constant.HOME_ACTIVITY)) {
			assertTrue(false, "HOME_ACTIVITY is not found");
		}

		e_my.click();// 点击：我的
		if (!Assist.isActivityDisplayed(Constant.LOGIN_ACTIVITY)) {
			assertTrue(false, "LOGIN_ACTIVITY is not found");
		}
		// 点击：账号密码登录
		e_account.click();
		e_molibe.sendKeys(mobile);
		e_password.sendKeys(password);
		e_login.click();
		// 登录成功Activity：LOGIN_ACTIVITY-->HOME_ACTIVITY
		if (!Assist.isActivityDisplayed(Constant.HOME_ACTIVITY)) {
			assertTrue(false, "登录后，没有回到：HOME_ACTIVITY");
		}

	}
}
