package top.base.utils;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import top.cases.Constant;

/**
 *  该类主要作用
 * 	1.测试开始前，初始化执行环境，初始化driver，给子类调用
 * 	2.测试完成后，清理执行环境
 *
 */
public class MyBaseCase {

	protected AndroidDriver<WebElement> mdriver;
	
	@BeforeClass
	public void setup() {
		if(mdriver==null){
			mdriver = Driver.newInstance();
		}
		System.out.println(mdriver.currentActivity());
		if (!mdriver.currentActivity().contains(Constant.SPLASH_ACTIVITY)) {
			CommandUtils.exec_shell("am start -n com.tude.android/.activity.SplashActivity");
		}
	}

	@AfterClass
	public void teardown() {
		
		if (mdriver != null) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CommandUtils.exec_shell("pm clear com.tude.android");
		}
	}

}
