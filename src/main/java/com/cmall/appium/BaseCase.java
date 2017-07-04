package com.cmall.appium;

import org.testng.Reporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseCase {
	
	protected static AndroidDriver<MobileElement> mdriver = null;
	TestManage manage = null;

	public void startAppiumServer() {
/*		AppiumServerUtils.getInstance().startServer("192.168.101.201", 4723);
		url = AppiumServerUtils.getInstance().getService().getUrl();*/
	}

	public AndroidDriver<MobileElement> setup() {
		Reporter.log("========== 正在准备测试环境，预计20s，请稍后 ==========", true);
//		mdriver = DriverFactory.getInstance().initAndroidDriver(url);
		mdriver= manage.initAndroidDriver(4723, "022TAS7N51009853");
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
