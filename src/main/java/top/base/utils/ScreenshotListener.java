package top.baseutils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import io.appium.java_client.android.AndroidDriver;

public class ScreenshotListener extends TestListenerAdapter {
	
	@Override
	public void onTestFailure(ITestResult tr) {
		AndroidDriver<WebElement> driver = Driver.newInstance();
		File location = new File("screenshots");
		String screenShotName = location.getAbsolutePath() + File.separator + tr.getMethod().getMethodName() + ".png";
		File screenShot = driver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(screenShotName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}