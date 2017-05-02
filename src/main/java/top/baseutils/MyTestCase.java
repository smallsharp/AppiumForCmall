package top.baseutils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

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
		logger.info("Execute-->--afterClass>mdriver.quit()");
		mdriver.quit();
	}

}
