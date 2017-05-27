package top.base.appium;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import io.appium.java_client.android.AndroidDriver;

public class TestNGListener extends TestListenerAdapter {

	private static Logger log = Logger.getLogger(TestNGListener.class);

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		log.info(tr.getName() + " Start");
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		log.info(tr.getName() + " Failure");
//		takeScreenShot(tr);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		log.info(tr.getName() + " Success");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		log.info(tr.getName() + " Skipped");
	}

	@Override
	public void onStart(ITestContext testContext) {
		super.onStart(testContext);
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
	}

	private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	
	private AndroidDriver<WebElement> mDriver;

	/**
	 * 
	 * @Description 获取截图，存在/screenshots目录下
	 * @Data 2017年5月3日
	 */
	private void takeScreenShot(ITestResult tr) {
		// 代码设置关闭escape-output
		System.setProperty(ESCAPE_PROPERTY, "false");
		String location = System.getProperty("user.dir")+"\\test-output\\screenshots";

		String screenShotName = location + File.separator + tr.getMethod().getMethodName() + "_"
				+ getCurrentDateTime() + ".jpg";
		File screenShot = mDriver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(screenShotName));
		} catch (IOException e) {
			log.info("执行截图失败！");
			e.printStackTrace();
		} finally {
//			Reporter.log("<img src=" + screenShotName + " width='360px' height='640px' /img>", true);
			Reporter.log("<img src=" + screenShotName + " /img>", true);// 将截图显示在报告中
		}
	}

	private static String getCurrentDateTime() {
		// 设置日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return sdf.format(new Date());
	}

}
