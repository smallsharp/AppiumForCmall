package top.play.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import top.base.appium.Helper;
import top.base.appium.Helper2;

/**
 * PO模式：登录页面
 * 
 * @author lee
 */
public class LoginPage {
	
	private AndroidDriver<MobileElement> mdriver;
	private Helper2 helper;
		
	public LoginPage() {
		// 这个空的构造方式是必须要的,init 页面需要
	}
	
	public LoginPage(AndroidDriver<MobileElement> driver) {
		this.mdriver = driver;
		helper = new Helper2(driver);
		System.out.println("L helper:"+helper);
	}
	
	@FindBy(id = "com.play.android:id/btn_jump")
	private WebElement e_skipVideo; 	// 跳过

	@AndroidFindBy(id="com.play.android:id/btn_profile")
	private WebElement e_my; 	// 我的

	@FindBy(id = "com.play.android:id/tv_account")
	private WebElement e_account; 	// 账号密码登录

	@FindBy(id = "com.play.android:id/et_account")
	private WebElement e_molibe; 	// 手机号

	@FindBy(id = "com.play.android:id/et_password")
	private WebElement e_password; 	// 密码

	@FindBy(id = "com.play.android:id/btn_login")
	private WebElement e_login; 	// 登录
	
	@FindBy(id="com.play.android:id/tv_sign_in")
	private MobileElement tv_sign_in;
	
	@FindBy(id="com.play.android:id/cb_confrim_deal")
	private MobileElement cb_confrim;
	
	
	/**
	 * 通过：手机号，密码登录
	 * 
	 * @param mobile
	 * @param password
	 */
	public void login(String mobile, String password) {
		
		try {
			String firstActivity = mdriver.currentActivity();
			if (firstActivity.contains(Play_ActivityList.HOME_ACTIVITY)) {
				e_my.click();// 点击：我的
			}
			
			if (!Helper.waitActivity(Play_ActivityList.LOGIN_ACTIVITY)) {
				assertEquals(mdriver.currentActivity(), Play_ActivityList.LOGIN_ACTIVITY);
			}
			
			e_account.click();
			e_molibe.sendKeys(mobile);
			Reporter.log("输入手机号："+ mobile,true);
			e_password.sendKeys(password);
			Reporter.log("输入密码："+ password,true);
			e_login.click();
			
			if (!Helper.waitActivity(firstActivity)) {
				assertEquals(mdriver.currentActivity(), firstActivity,"登录成功后，应该返回:"+firstActivity);
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			assertTrue(false,"failed to locate element!");
		} catch (Exception e){
			e.printStackTrace();
			assertTrue(false,"occurred error while running!");
		}

	}
	
	
	/**
	 * 通过：手机号，密码登录
	 * 
	 * @param mobile
	 * @param password
	 */
	public void login_help(String mobile, String password) {
		
		try {
			String firstActivity = mdriver.currentActivity();
			if (firstActivity.contains(Play_ActivityList.HOME_ACTIVITY)) {
				e_my.click();// 点击：我的
			}
			
			if (!helper.waitActivity(Play_ActivityList.LOGIN_ACTIVITY)) {
				assertEquals(mdriver.currentActivity(), Play_ActivityList.LOGIN_ACTIVITY);
			}
			
			e_account.click();
			e_molibe.sendKeys(mobile);
			Reporter.log("输入手机号："+ mobile,true);
			e_password.sendKeys(password);
			Reporter.log("输入密码："+ password,true);
			e_login.click();
			
			if (!helper.waitActivity(firstActivity)) {
				assertEquals(mdriver.currentActivity(), firstActivity,"登录成功后，应该返回:"+firstActivity);
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			assertTrue(false,"failed to locate element!");
		} catch (Exception e){
			e.printStackTrace();
			assertTrue(false,"occurred error while running!");
		}

	}
	
	/**
	 * 嵌套登录使用
	 * @param mobile
	 * @param password
	 */
	public void login2(String mobile, String password) {
		
		try {
			
			String previousActivity = mdriver.currentActivity();
			
			if (previousActivity.contains(Play_ActivityList.HOME_ACTIVITY)) {
				e_my.click();// 点击：我的
			}

			if (!Helper.waitActivity(Play_ActivityList.LOGIN_ACTIVITY)) {
				assertEquals(mdriver.currentActivity(), Play_ActivityList.LOGIN_ACTIVITY);
			}
			
			MobileElement m_accout = mdriver.findElementById("com.play.android:id/tv_account");
			m_accout.click();
			
			MobileElement m_moblie = mdriver.findElementById("com.play.android:id/et_account");
			
			Helper.inputText(m_moblie, mobile);
			Reporter.log("输入手机号："+ mobile,true);
			
			MobileElement m_password = mdriver.findElementById("com.play.android:id/et_password");
			Helper.inputText(m_password, password);
			Reporter.log("输入密码："+ password,true);
			
			MobileElement m_login = mdriver.findElementById("com.play.android:id/btn_login");
			Helper.clickonElement(m_login);
			
			if (!Helper.waitActivity(previousActivity)) {
				assertEquals(mdriver.currentActivity(), previousActivity,"登录成功后，应该返回:"+previousActivity);
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			assertTrue(false,"failed to locate element!");
		} catch (Exception e){
			e.printStackTrace();
			assertTrue(false,"occurred error while running!");
		}

	}
	
}
