package com.cmall.tude.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cmall.appium.AppiumServerUtil;
import com.cmall.appium.TestNGListener;
import com.cmall.play.pages.LoginPage;
import com.cmall.play.pages.PersonPage;
import com.cmall.utils.CommandUtil;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


@Listeners({TestNGListener.class})
public class TestSuite_Tude{
	private AndroidDriver<MobileElement> mdriver = null;

	@BeforeClass(alwaysRun = true)
	public void startAppiumServer() {
//		url = AppiumServerUtils.getInstance().startServer("127.0.0.1", 4723);
//		url = AppiumServerUtils.getInstance().startAppiumServerByDefault();
	}

	@BeforeClass(alwaysRun = true, dependsOnMethods = { "startAppiumServer" })
	public void setup() {
		Reporter.log("========== 正在准备测试环境，预计20s，请稍后 ==========", true);
		if (mdriver == null) {
//			mdriver = DriverFactory.getInstance().initAndroidDriver();
		}
	}

	@AfterClass
	public void teardown() throws InterruptedException {

		if (mdriver != null) {
			Thread.sleep(2000);
			mdriver.closeApp();
			CommandUtil.exec_shell("pm clear com.play.android");
		}
//		AppiumServerUtil.getInstance().stopServer();
		Reporter.log("========== 测试执行完成，清理测试环境通过 ==========", true);

	}

		
	@Test(description="登录")
	@Parameters({"mobile","password"})//参数名称需要和xml中对应
	public void testLogin(String mobile,String password) throws InterruptedException {
		LoginPage login = PageFactory.initElements(mdriver, LoginPage.class);
		login.login(mobile, password);
	}
	
	@Test(description="退出登录")
	public void testLogout(){
		PersonPage personPage = PageFactory.initElements(mdriver, PersonPage.class);
		personPage.logout();
	}
	
	@Test(description="购买男装示例")
	public void testBuyMenWear(){
		DIYPage diyPage = PageFactory.initElements(mdriver, DIYPage.class);
		diyPage.buyMenWear();
	}
	

}
