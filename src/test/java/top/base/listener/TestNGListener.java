package top.base.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
		try {
			takeScreenShot(tr);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * @throws InterruptedException 
	 * @Description 获取截图，存在/screenshots目录下
	 * @Data 2017年5月3日
	 */
	private void takeScreenShot(ITestResult tr) throws InterruptedException {
		
		System.setProperty(ESCAPE_PROPERTY, "false");  		// 代码设置关闭escape-output

		// 在工作目录下创建文件夹，用来存放图片的
		File location = new File("screenshots"); 
		String screenShotName = location.getAbsolutePath() + File.separator + tr.getMethod().getMethodName() + "_"
				+ getCurrentDateTime() + ".jpg";
		File screenShot = mDriver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(screenShotName));
			// width='425px' height='875px'
//			Reporter.log("<img src="+screenShotName + " width='360px' height='640px'"+ "/img>", true);
		} catch (IOException e) {
			System.out.println("截图失败了…");
			e.printStackTrace();
		} finally{
			// 将截图显示在报告当中
			Reporter.log("<img src="+screenShotName + " width='360px' height='640px' /img>", true);		
			}


	}
	
	private static String getCurrentDateTime() {
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return df.format(new Date());
	}

}
