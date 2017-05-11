package top.biz;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import top.base.utils.Driver;

public class Biz_login {

	public static AndroidDriver<WebElement> mDriver = Driver.newInstance();

	/**
	 * 通过账号、密码登录
	 * @author lee
	 * @param mobile
	 * @param pwd
	 */
	public static void login(String mobile, String pwd) {
		
		//获取手机号，并输入
		mDriver.findElementById(Res.MOBILE_ID).sendKeys(mobile);
		
		//获取验证码，并输入
		mDriver.findElementById(Res.PWD_ID).sendKeys(pwd);
		
		//获取登录按钮，并点击
		mDriver.findElementById(Res.LOGIN_ID).click();
				
	}
}
