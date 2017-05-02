package top.baseutils;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNGListener extends TestListenerAdapter {

	private static Logger logger = Logger.getLogger(TestNGListener.class);
	
	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info(tr.getName() + " Start");
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.info(tr.getName() + " Failure");
		takeScreenShot(tr);
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.info(tr.getName() + " Success");
	}


	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.info(tr.getName() + " Skipped");
		takeScreenShot(tr);
	}
	
	@Override
	public void onStart(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onStart(testContext);
		logger.info(testContext.getName()+" Start");
	}


	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
		logger.info(testContext.getName()+" Finish");

	}

/**
	 * 自动截图，保存图片到本地以及html结果文件中
	 * 
	 * @param tr
	 */
	private void takeScreenShot(ITestResult tr) {
		
	}
}
