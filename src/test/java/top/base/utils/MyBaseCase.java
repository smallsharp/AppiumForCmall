package top.base.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
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
	Logger logger = Logger.getLogger(MyBaseCase.class);

	@BeforeSuite
	public void beforeClass() {
		
		if(mdriver==null){
			mdriver = Driver.newInstance();
		}
	}

	@AfterSuite
	public void afterClass() {
		
		if (mdriver != null) {
			mdriver.quit();
		}
	}

}