package com.cmall.appium;

import org.testng.Reporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseCase {
	
	protected static AndroidDriver<MobileElement> mdriver = null;

	/*	@BeforeClass(alwaysRun = true)
	public void startAppiumServer() {
		AppiumServerUtils.getInstance().startServer("192.168.101.201", 4723);
		url = AppiumServerUtils.getInstance().getService().getUrl();
	}*/

//	@BeforeClass(alwaysRun = true, dependsOnMethods = { "startAppiumServer" })
/*	@BeforeSuite
	public void setup() {
		Reporter.log("========== 正在准备测试环境，预计20s，请稍后 ==========", true);
//		mdriver = DriverFactory.getInstance().initAndroidDriver(url);
		mdriver= DriverFactory.getInstance().initAndroidDriver();
		Helper.setDriver(mdriver);
		
	}*/
	
	public static AndroidDriver<MobileElement> setup() {
		Reporter.log("========== 正在准备测试环境，预计20s，请稍后 ==========", true);
//		mdriver = DriverFactory.getInstance().initAndroidDriver(url);
		mdriver= DriverFactory.getInstance().initAndroidDriver();
//		Helper.setDriver(mdriver);
		return mdriver;
		
	}

	public void teardown() throws InterruptedException {

		if (mdriver != null) {
//			CommandUtil.exec_shell("pm clear com.play.android");
			mdriver.quit();
		}

//		AppiumServerUtils.getInstance().stopServer();
		Reporter.log("========== 测试用例执行完成，BYE BYE ==========", true);
	}
	
}
