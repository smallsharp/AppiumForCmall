package top.base.listener;

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

import top.base.utils.Driver;
import top.base.utils.MyAndroidDriver;

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
		takeScreenShot(tr);
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

	private static MyAndroidDriver<WebElement> mDriver = Driver.newInstance();
	
	private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";  
	  
	/**
	 * 
	 * 
	 * @Description 获取截图，存在/screenshots目录下
	 * @Data 2017年5月3日
	 */
	private void takeScreenShot(ITestResult tr) {
		// 代码设置关闭escape-output
		System.setProperty(ESCAPE_PROPERTY, "false"); 
//		Reporter.log("<br>",true);

		// 在默认的工作目录下面创建一个名字叫screenshots1的文件夹，用来存放图片的
		File location = new File("screenshots"); 
		String screenShotName = location.getAbsolutePath() + File.separator + tr.getMethod().getMethodName() + "_"
				+ getCurrentDateTime() + ".png";
		File screenShot = mDriver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(screenShotName));
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			// 将截图显示在报告当中
			Reporter.log("<img src="+screenShotName + " width='425px' height='875px' /img>", true);
		}
	}

	private static String getCurrentDateTime() {
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return df.format(new Date());
	}

}
