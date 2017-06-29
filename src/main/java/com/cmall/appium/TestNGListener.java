package com.cmall.appium;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.cmall.utils.LogUtil;

public class TestNGListener extends TestListenerAdapter {

	static LogUtil log = new LogUtil(TestNGListener.class);

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		log.info("\n"+tr.getName() + " Start");
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
//		takeScreenShot(tr);
		log.info("\n"+tr.getName() + " Failure");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		log.info("\n"+tr.getName() + " Success");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		log.info("/n"+tr.getName() + " Skipped");
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
	
	/**
	 * 
	 * @Description 获取截图，存在/screenshots目录下
	 * @Data 2017年5月3日
	 */
	private void takeScreenShot(ITestResult tr) {
		
		// 代码设置关闭escape-output
		System.setProperty(ESCAPE_PROPERTY, "false");

		String screenShotName = tr.getMethod().getMethodName() + "_"
				+ getCurrentDateTime() + ".jpg";
		
//		Helper.takeScreenShot(screenShotName);
		
		Reporter.log("\n"+"<img src=" + "test-output\\screenshots\\"+screenShotName + " /img>", true);// 将截图显示在报告中
	}

	private static String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return sdf.format(new Date());
	}

}
