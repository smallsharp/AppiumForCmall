package com.cmall.test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.cmall.appium.AppiumServerUtil;
import com.cmall.appium.DriverProduce;
import com.cmall.appium.IBaseCase;
import com.cmall.play.pages.LoginPage;
import com.cmall.play.pages.ModelPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * 使用接口
 * @author cm
 *
 */
public class ModelTestCase01 implements IBaseCase{
	
	private AndroidDriver<MobileElement> mdriver;
	private AppiumServerUtil appiumSvr = new AppiumServerUtil();
	private String ip = "127.0.0.1";
	private int port = 4723;
	private String deviceName = "127.0.0.1:62001";

	@BeforeSuite
	public void setup() {
		Reporter.log("========== 正在准备测试环境，预计20s，请稍后 ==========", true);
		appiumSvr.startServer(ip, port, deviceName);
		mdriver = DriverProduce.initDriver(ip, port, deviceName);
	}

	@AfterSuite
	public void teardown() {
		if (mdriver != null) {
			mdriver.quit();
		}
		appiumSvr.stopServer();
		Reporter.log("========== 测试用例执行完成，BYE BYE ==========", true);
	}

	@Test(description=("登录"),priority=10)
	public void testLogin() {
		LoginPage loginPage = new LoginPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS), loginPage);
		loginPage.login("18521035133", "111111");
	}
	
	@Test(description = "切换商品，查看模型展示",priority=20)
	public void test3DModelGoodsList_01() {
		ModelPage modelPage = new ModelPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS),modelPage);
		modelPage.check3DModelGoodsList();
	}
}
