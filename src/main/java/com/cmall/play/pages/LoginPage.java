package com.cmall.play.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import com.cmall.appium.Helper;
import com.cmall.utils.LogUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * PO模式的简单运用
 * @author lee
 */
public class LoginPage {
	
	private AndroidDriver<MobileElement> mdriver;
	private Helper helper;
	private LogUtil log = new LogUtil(LoginPage.class);

	public LoginPage() {
		// 这个空的构造方式是必须要的,init 页面需要
	}

	public LoginPage(AndroidDriver<MobileElement> driver) {
		this.mdriver = driver;
		helper = new Helper(driver);
	}

	@FindBy(id = "com.play.android:id/btn_jump")
	private MobileElement e_skipVideo; // 跳过

	@AndroidFindBy(id = "com.play.android:id/btn_profile")
	private MobileElement m_my; // 我的

	@FindBy(id = "com.play.android:id/tv_account")
	private MobileElement e_account; // 账号密码登录

	@FindBy(id = "com.play.android:id/et_account")
	private MobileElement e_molibe; // 手机号

	@FindBy(id = "com.play.android:id/et_password")
	private MobileElement e_password; // 密码

	@FindBy(id = "com.play.android:id/btn_login")
	private MobileElement e_login; // 登录

	@FindBy(id = "com.play.android:id/tv_sign_in")
	private MobileElement tv_sign_in;

	@FindBy(id = "com.play.android:id/cb_confrim_deal")
	private MobileElement cb_confrim;

	/**
	 * 通过：手机号，密码登录
	 * 
	 * @param mobile
	 * @param password
	 */
	public void login(String mobile, String password) {

		try {
			
			String previousActivity = helper.getCurrentActivity();
			if (previousActivity.contains(Play_ActivityList.HOME_ACTIVITY)) {
				helper.clickonElement(m_my);
			}

			if (!helper.waitActivity(Play_ActivityList.LOGIN_ACTIVITY)) {
				assertEquals(mdriver.currentActivity(), Play_ActivityList.LOGIN_ACTIVITY);
			}

			helper.clickonElement(e_account);
			helper.inputText(e_molibe, mobile);
//			Reporter.log("输入手机号：" + mobile, true);
			log.info("输入手机号：" + mobile);
			helper.inputText(e_password, password);
//			Reporter.log("输入密码：" + password, true);
			log.info("输入密码：" + password);
			helper.clickonElement(e_login);

			if (!helper.waitActivity(previousActivity)) {
				assertEquals(mdriver.currentActivity(), previousActivity, "登录成功后，应该返回:" + previousActivity);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 嵌套登录使用
	 * 
	 * @param mobile
	 * @param password
	 */
	public void login2(String mobile, String password) {

		try {
			String previousActivity = mdriver.currentActivity();
			if (previousActivity.contains(Play_ActivityList.HOME_ACTIVITY)) {
				MobileElement m_my = mdriver.findElementById("com.play.android:id/btn_profile");
				m_my.click();
			}

			if (!helper.waitActivity(Play_ActivityList.LOGIN_ACTIVITY)) {
				assertEquals(mdriver.currentActivity(), Play_ActivityList.LOGIN_ACTIVITY);
			}

			MobileElement m_accout = mdriver.findElementById("com.play.android:id/tv_account");
			m_accout.click();

			MobileElement m_moblie = mdriver.findElementById("com.play.android:id/et_account");

			helper.inputText(m_moblie, mobile);
			Reporter.log("输入手机号：" + mobile, true);

			MobileElement m_password = mdriver.findElementById("com.play.android:id/et_password");
			helper.inputText(m_password, password);
			Reporter.log("输入密码：" + password, true);

			MobileElement m_login = mdriver.findElementById("com.play.android:id/btn_login");
			helper.clickonElement(m_login);

			if (!helper.waitActivity(previousActivity)) {
				assertEquals(mdriver.currentActivity(), previousActivity, "登录成功后，应该返回:" + previousActivity);
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			assertTrue(false, "failed to locate element!");
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false, "occurred error while running!");
		}

	}

}
