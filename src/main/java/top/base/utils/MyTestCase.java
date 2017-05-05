package top.base.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * 该类主要作用
 * 	1.测试开始前，初始化执行环境，初始化driver，给子类调用
 * 	2.测试完成后，清理执行环境
 * @author lee
 *
 */

public class MyTestCase {

	protected MyAndroidDriver<WebElement> mdriver;
	Logger logger = Logger.getLogger(MyTestCase.class);

	@BeforeClass
	public void beforeClass() {
		logger.info("Execute-->beforeClass-->Driver.newInstance()");
		mdriver = Driver.newInstance();
	}

	@AfterClass
	public void afterClass() {
		logger.info("Execute-->afterClass-->mdriver.quit()");
		if (mdriver != null) {
			mdriver.quit();
		}
	}

}
