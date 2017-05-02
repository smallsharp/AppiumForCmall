package top.biz;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import top.baseutils.Driver;
import top.baseutils.MyAndroidDriver;

public class Biz_login {

	public static MyAndroidDriver<WebElement> mDriver = Driver.newInstance();

	static Logger logger = Logger.getLogger(Biz_login.class);

	/**
	 * 登录Api
	 * 
	 * @param mobile
	 * @param code
	 */
	public static void login(String mobile, String code) {
		
		//获取手机号，并输入
		mDriver.findElementById(Res.MOBILE_ID).sendKeys(mobile);
		
		//获取验证码，并输入
		mDriver.findElementById(Res.CODE_ID).sendKeys(code);
		
		//获取登录按钮，并点击
		mDriver.findElementById(Res.LOGIN_ID).click();
				
	}
}
