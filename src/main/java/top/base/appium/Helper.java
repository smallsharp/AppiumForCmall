package top.base.appium;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import top.base.utils.LogUtil;
import top.play.pages.ActivityList;

public class Helper {

	static LogUtil log = new LogUtil(Helper.class);

	private static AndroidDriver<MobileElement> mdriver;

	public static AndroidDriver<MobileElement> getmDriver() {
		return mdriver;
	}

	public static void setDriver(AndroidDriver<MobileElement> mdriver) {
		Helper.mdriver = mdriver;
	}

	/**
	 * 
	 * @Description 动态等待activity出现
	 * @Data 2017年5月3日
	 * @return true or false
	 * @throws InterruptedException
	 */
	public static boolean waitActivity(String activityName) {
		
		log.info("\n"+"Waiting activity to appear ==> " + "(" + activityName + ")");

		for (int i = 0; i < 5; i++) {
			if (activityName.contains(mdriver.currentActivity())) {
				log.info("\n" + "Found activity ==> "+ "(" + activityName + ")");
				return true;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.info("\n"+"ActivityNotFound:" + "(" + activityName + ")" +"\n"+"CurrentActivity is:" + "(" + mdriver.currentActivity() + ")");
		return false;
	}

	/**
	 * 
	 * @param element
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean waitElement(MobileElement element) throws InterruptedException {
		
		for (int j = 0; j < 5; j++) {

			log.info("\n" + "Waiting element to appear ==> " + "(" + splitElement(element) + ")");

			if (element.isDisplayed()) {
				log.info("\n"+"Found element ==> " +  "(" + splitElement(element) + ")");
				Thread.sleep(500);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 点击
	 * @param element
	 */
	public static void clickonElement(MobileElement element){
		log.info("\n"+"click on element ==> " + "(" + splitElement(element) + ")" );
		element.click();
	}
	
	/**
	 * 输入
	 * @param element
	 * @param text
	 */
	public static void inputText(MobileElement element,CharSequence... text){
		log.info("\n"+"input text to element ==> " + "(" + splitElement(element) + ")");
		element.sendKeys(text);
	}
	
	/**
	 * 点击系统按键
	 * @param androidkeycode
	 */
	public static void pressKeyCode(int androidkeycode){
		log.info("\n"+"Press AndroidKeyCode ==> "+ androidkeycode);
		mdriver.pressKeyCode(androidkeycode);
	}
	
	/***
	 * 切换WEB页面查找元素WEBVIEW、 NATIVE_APP
	 */
	public static void switchToWebView() {

		Set<String> ContextHandles = mdriver.getContextHandles();
		log.info("\n"+"All ContextHandles :" + ContextHandles);
		for (String contextName : ContextHandles) {
			if (contextName.contains("WEBVIEW") || contextName.contains("webview")) {
				mdriver.context(contextName);
				log.info("\n"+"Swtich to Webview success :" + contextName);
				break;
			}
		}
	}

	/***
	 * 检查网络
	 * 
	 * @return 是否正常
	 */
	public boolean checkNet() {
		String text = mdriver.getConnection().toString();
		if (text.contains("Data: true"))
			return true;
		else
			return false;
	}

	/***
	 * 根据UIautomator底层方法得到对应desc的view
	 * 
	 * @param desc名
	 * @return View
	 */
	public static MobileElement getViewbyUidesc(String name) {
		return mdriver.findElementByAndroidUIAutomator("new UiSelector().descriptionContains(\"" + name + "\")");
	}

	/***
	 * 根据UIautomator底层方法得到对应text的view
	 * 
	 * @param text名
	 * @return View
	 */
	public static MobileElement getViewbyUitext(String name) {
		return mdriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + name + "\")");
	}
	
	/**
	 * 截图，将图片放在test-output\\screenshots中
	 * 
	 * @param screenShotName
	 */
	public static void takeScreenShot(String screenShotName) {

		String path = "test-output\\screenshots\\";
		
		File screenShot = mdriver.getScreenshotAs(OutputType.FILE);

		try {
			File destFile = new File(path + screenShotName);
			FileUtils.copyFile(screenShot, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("\n" + "截图成功：" + screenShotName);
	}

	/***
	 * 下滑1/4屏幕
	 */
	public static void slideDown() {
		int x = mdriver.manage().window().getSize().width;
		int y = mdriver.manage().window().getSize().height;
		mdriver.swipe(x / 2, y * 3 / 10, x / 2, y * 7 / 10, 0);
	}

	/***
	 * 左滑1/2屏幕
	 */
	public void slideLeft() {
		int x = mdriver.manage().window().getSize().width;
		int y = mdriver.manage().window().getSize().height;
		mdriver.swipe(x / 4 * 3, y / 2, x / 4 * 1, y / 2, 0);
	}

	/***
	 * 特殊左滑
	 * 
	 * @param 传入从上到下宽度的百分比(1-99之间)
	 */
	public void slideLeft(int i) {
		Assert.assertFalse(i <= 0 || i >= 100, "左滑宽度传入错误");
		int x = mdriver.manage().window().getSize().width;
		int y = mdriver.manage().window().getSize().height;
		mdriver.swipe(x / 4 * 3, y / 10 * i, x / 4 * 2, y / 10 * i, 0);
	}

	public static void swipeUpUntilFind(String str) {

		int width = mdriver.manage().window().getSize().width;
		int height = mdriver.manage().window().getSize().height;

		for (int i = 0; i < 5; i++) {
			mdriver.swipe(width / 2, height * 7 / 10, width / 2, height * 3 / 10, 500);
			if (mdriver.getPageSource().contains(str)) {
				break;
			}
		}

	}


	public static void backToHomeActivity() {
		
		try {
			
			if (ActivityList.HOME_ACTIVITY.equals(mdriver.currentActivity())) {
				return;
			}
			
			log.info("\n" + "Run：backToHomeActivity");

			for (int i = 0; i < 6; i++) {
				
				if (ActivityList.HOME_ACTIVITY.equals(mdriver.currentActivity())) {
					return;
				}
				Thread.sleep(2500);
				pressKeyCode(AndroidKeyCode.BACK);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取操作的控件字符串
	private static String splitElement(MobileElement element) {
		// 用"->"分割，分成数组，取下标为1的
		// [[MyAndroidDriver:  on LINUX (750e968d-5203-408c-9407-cf695a5eb436)] -> id: com.tude.android:id/btn_jump]
		String str = element.toString().split("-> ")[1];
		return str.substring(0, str.length() - 1);
	}
}