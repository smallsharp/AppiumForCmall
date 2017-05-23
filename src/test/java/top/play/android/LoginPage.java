package top.play.android;

import static org.testng.Assert.assertEquals;
import java.io.File;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.android.AndroidDriver;
import top.base.utils.Helper;
import top.base.utils.ImageUtil;

/**
 * PO模式：登录页面
 * 
 * @author lee
 */
public class LoginPage {

	@FindBy(id = "com.tude.android:id/btn_jump")
	private WebElement e_skipVideo; 	// 跳过

	@FindBy(id = "com.tude.android:id/btn_profile")
	private WebElement e_my; 	// 我的

	@FindBy(id = "com.tude.android:id/tv_account")
	private WebElement e_account; 	// 账号密码登录

	@FindBy(id = "com.tude.android:id/et_account")
	private WebElement e_molibe; 	// 手机号

	@FindBy(id = "com.tude.android:id/et_password")
	private WebElement e_password; 	// 密码

	@FindBy(id = "com.tude.android:id/btn_login")
	private WebElement e_login; 	// 登录
	
	
	private AndroidDriver<WebElement> mDriver;

	/**
	 * 通过：手机号，密码登录
	 * 
	 * @param mobile
	 * @param password
	 */
	public void login(String mobile, String password) {

		try {

			// 如果页面是在视频页面，则点击"跳过"
			if (Helper.isActivityDisplayed(ActivityList.VIDEO_ACTIVITY)) {
				e_skipVideo.click();
			}
			
			// 如果当前页面不在HOME_ACTIVITY，则测试失败
			if (!Helper.isActivityDisplayed(ActivityList.HOME_ACTIVITY)) {
				assertEquals(mDriver.currentActivity(), ActivityList.HOME_ACTIVITY);
			}

			e_my.click();// 点击：我的
			
			if (!Helper.isActivityDisplayed(ActivityList.LOGIN_ACTIVITY)) {
				assertEquals(mDriver.currentActivity(), ActivityList.LOGIN_ACTIVITY);
			}
			Thread.sleep(2000);
			
			String screenName = "login_actul.jpg";
			Helper.takeScreenShot(screenName);

			File imgA = new File(Constant.EXPECTED_PATH,Constant.LOGIN_EXP);
			File imgB = new File(Constant.ACTUL_PATH,screenName);

			ImageUtil.getSamePercentFrom(imgA, imgB);
			
			// 点击：账号密码登录
			e_account.click();
			e_molibe.sendKeys(mobile);
			e_password.sendKeys(password);
			e_login.click();
			// 登录成功Activity：LOGIN_ACTIVITY-->HOME_ACTIVITY
			if (!Helper.isActivityDisplayed(ActivityList.HOME_ACTIVITY)) {
				assertEquals(mDriver.currentActivity(), ActivityList.HOME_ACTIVITY,"登录成功后，应该返回:HOME_ACTIVITY");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
