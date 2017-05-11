package top.base.utils;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 *  该类主要作用
 * 	1.测试开始前，初始化执行环境，初始化driver，给子类调用
 * 	2.测试完成后，清理执行环境
 *
 */
public class MyBaseCase {

	protected MyAndroidDriver<WebElement> mdriver;
	
	@BeforeSuite
	public void setup() {
		Reporter.log("111",true);
		mdriver = Driver.newInstance();
		if(mdriver==null){
			Reporter.log("driver is still null"+true);

		}
	}

	@AfterSuite
	public void teardown() {
		
		if (mdriver != null) {
//			mdriver.quit();
			mdriver.resetApp();
			Reporter.log("========== 测试完成，清理测试环境 ==========",true);
		}
	}

}
