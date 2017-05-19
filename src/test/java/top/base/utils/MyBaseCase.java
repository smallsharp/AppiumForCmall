package top.base.utils;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;

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
		System.out.println("exec:setup()");
		if(mdriver==null){
			mdriver = Driver.newInstance();
		}
	}

	@AfterClass
	public void teardown() throws InterruptedException {

		if (mdriver != null) {
			System.out.println("exec:teardown()");
			Thread.sleep(2000);
			mdriver.closeApp();
			CommandUtils.exec_shell("pm clear com.tude.android");
		}
	}

}
