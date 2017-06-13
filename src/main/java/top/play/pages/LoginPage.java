package top.play.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import top.base.appium.Helper;

/**
 * PO模式：登录页面
 * 
 * @author lee
 */
public class LoginPage {
	
	private AndroidDriver<MobileElement> driver;
	
	public LoginPage() {
		// 这个空的构造方式是必须要的,init 页面需要
	}
	
	public LoginPage(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
	}
	
	public void setDriver(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
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
			Helper.setDriver(driver);
			
			String firstActivity = driver.currentActivity();
			
			if (firstActivity.contains(ActivityList.HOME_ACTIVITY)) {
				e_my.click();// 点击：我的
			}
			
			if (!Helper.waitActivity(ActivityList.LOGIN_ACTIVITY)) {
				assertEquals(driver.currentActivity(), ActivityList.LOGIN_ACTIVITY);
			}
			
			e_account.click();
			e_molibe.sendKeys(mobile);
			Reporter.log("输入手机号："+ mobile,true);
			e_password.sendKeys(password);
			Reporter.log("输入密码："+ password,true);
			e_login.click();
			
			if (!Helper.waitActivity(firstActivity)) {
				assertEquals(driver.currentActivity(), firstActivity,"登录成功后，应该返回:"+firstActivity);
			}

		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false,"登录用例执行失败！");
		}

	}
	
	/**
	 * 嵌套登录使用
	 * @param mobile
	 * @param password
	 */
	public void login2(String mobile, String password) {

		try {
			Helper.setDriver(driver);
			
			String firstActivity = driver.currentActivity();
			
			if (firstActivity.contains(ActivityList.HOME_ACTIVITY)) {
				e_my.click();// 点击：我的
			}
			
			if (!Helper.waitActivity(ActivityList.LOGIN_ACTIVITY)) {
				assertEquals(driver.currentActivity(), ActivityList.LOGIN_ACTIVITY);
			}
			
			MobileElement m_accout = driver.findElementById("com.play.android:id/tv_account");
			m_accout.click();
			
			MobileElement m_moblie = driver.findElementById("com.play.android:id/et_account");
			m_moblie.sendKeys(mobile);
			Reporter.log("输入手机号："+ mobile);
			
			MobileElement m_password = driver.findElementById("com.play.android:id/et_password");
			m_password.sendKeys(password);
			Reporter.log("输入密码："+ password);
			
			MobileElement m_login = driver.findElementById("com.play.android:id/btn_login");
			m_login.click();
			
			if (!Helper.waitActivity(firstActivity)) {
				assertEquals(driver.currentActivity(), firstActivity,"登录成功后，应该返回:"+firstActivity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void signIn(){
		
		try {
			
			e_my.click();// 点击：我的
			Helper.setDriver(driver);
			if (!Helper.waitActivity(ActivityList.LOGIN_ACTIVITY)) {
				assertEquals(driver.currentActivity(), ActivityList.LOGIN_ACTIVITY);
			}
			
			tv_sign_in.click();
			// 已经勾选，应该为True
			System.out.println("Shoule be true:"+cb_confrim.getAttribute("checked"));

			cb_confrim.click();
			// 去掉勾选，应该为False
			System.out.println("Shoule be false:"+cb_confrim.getAttribute("checked"));

			Thread.sleep(2000);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
