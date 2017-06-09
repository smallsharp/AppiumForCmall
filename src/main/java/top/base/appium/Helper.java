package top.base.appium;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class Helper {

	private static final Logger log = Logger.getLogger(Helper.class);
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
	 */
	public static boolean waitActivity(String activityName) {
		boolean flag = false;
		int i = 1;
		while (i <= 20) {
			try {
				if (activityName.contains(mdriver.currentActivity())) {
					log.info(activityName + " Found!");
					flag = true;
					break;
				} else {
//					log.info(activityName + ",第" + i + "次等待，Not Found!");
					i++;
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				i++;
				log.error(activityName + ",第" + i + "次等待，Not Found!");
			}
		}
		return flag;
	}

	static WebElement element = null;

	/**
	 * 
	 * @Description 通过元素id等，查询多次，定位元素
	 * @Data 2017年5月3日
	 * @return WebElement
	 */
	public static WebElement findElementById(String resourceId) throws InterruptedException {
		int i = 1;
		while (i <= 3) {
			try {
				// 如果找不到，会抛出异常
				if ((element = mdriver.findElementById(resourceId)).isDisplayed()) {
					log.info(resourceId + ",控件 found！");
					break;
				}
			} catch (Exception e) {
				log.error(resourceId + ",第" + i + "次等待，Not Found!");
				i++;
			}
		}
		return element;
	}
	
	public static boolean isElementDisplayed(WebElement webElement) throws InterruptedException {
		
		boolean flag = false;
		int i = 1;
		while (i <= 3) {
				if (webElement.isDisplayed()) {
					log.info(webElement + ",控件 found！");
					flag = true;
					break;
				} else{
					log.info(webElement + ",控件 found！");
					i++;
				}
		}
		return flag;
	}
	
	/***
	* 切换WEB页面查找元素
	*/
	public static void switchToWebView() {

		Set<String> ContextHandles = mdriver.getContextHandles();
		System.out.println("ContextHandles:"+ContextHandles);
		for (String contextName : ContextHandles) {
			if (contextName.contains("WEBVIEW") || contextName.contains("webview")) {
				mdriver.context(contextName);
				System.out.println("切换到Webview页面成功");
				break;
			}
		}
	}
	
	
	public static void switchToNative() {
		
		Set<String> ContextHandles = mdriver.getContextHandles();
		System.out.println("ContextHandles:"+ContextHandles);
		for (String contextHandle:ContextHandles) {
			if (contextHandle.contains("NATIVE_APP")) {
				mdriver.context(contextHandle);
				System.out.println("切换到Navtive页面成功");
				break;
			}
		}
	}

	public void clearText(WebElement element) {

		String text = element.getText();
		mdriver.pressKeyCode(AndroidKeyCode.KEYCODE_MOVE_END);// 123

		for (int i = 0; i < text.length(); i++) {
			mdriver.pressKeyCode(AndroidKeyCode.KEYCODE_DEL);// 67
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
	public static WebElement getViewbyUidesc(String name) {
		return mdriver.findElementByAndroidUIAutomator("new UiSelector().descriptionContains(\"" + name + "\")");
	}

	/***
	 * 根据UIautomator底层方法得到对应text的view
	 * 
	 * @param text名
	 * @return View
	 */
	public static WebElement getViewbyUitext(String name) {
		return mdriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + name + "\")");
	}

	public static String getScreen() {
		String fileRoute = "路径";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		String picname = fileRoute + df.format(new Date()).toString() + ".png";
		File screen = mdriver.getScreenshotAs(OutputType.FILE);
		System.out.println(picname);
		File screenFile = new File(picname);
		try {
			FileUtils.copyFile(screen, screenFile);
			String time = df.format(new Date()).toString();
			System.out.println("当前时间" + time);
			return time;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 上滑1/4屏幕
	 */
	public static void slideUP() {
		int x = mdriver.manage().window().getSize().width;
		int y = mdriver.manage().window().getSize().height;
		mdriver.swipe(x / 2, y * 7 / 10, x / 2, y * 3 / 10, 0);
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
	 * 右滑1/2屏幕
	 */
	public void slideRight() {
		int x = mdriver.manage().window().getSize().width;
		int y = mdriver.manage().window().getSize().height;
		mdriver.swipe(x / 4 * 1, y / 2, x / 4 * 3, y / 2, 0);
	}

	/***
	 * 特殊上滑
	 * 
	 * @param 传入从左到右宽度的百分比(1-99之间)
	 */
	public void slideUP(int i) {
		Assert.assertFalse(i <= 0 || i >= 100, "上滑宽度传入错误");
		int x = mdriver.manage().window().getSize().width;
		int y = mdriver.manage().window().getSize().height;
		mdriver.swipe(x / 2, y * 7 / 10, x / 2, y * 3 / 10, 0);
	}

	/***
	 * 特殊下滑
	 * 
	 * @param 传入从左到右宽度的百分比(1-99之间)
	 */
	public void slideDown(int i) {
		Assert.assertFalse(i <= 0 || i >= 100, "下滑宽度传入错误");
		int x = mdriver.manage().window().getSize().width;
		int y = mdriver.manage().window().getSize().height;
		mdriver.swipe(x / 2, y * 3 / 10, x / 2, y * 7 / 10, 0);
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

	/***
	 * 特殊右滑
	 * 
	 * @param 传入从上到下宽度的百分比(1-99之间)
	 */
	public void slideRight(int i) {
		Assert.assertFalse(i <= 0 || i >= 100, "左滑宽度传入错误");
		int x = mdriver.manage().window().getSize().width;
		int y = mdriver.manage().window().getSize().height;
		mdriver.swipe(x / 4 * 2, y / 10 * i, x / 4 * 3, y / 10 * i, 0);
	}

	// 向左滑动
	public static void swipeToLeft(int times) throws InterruptedException {

		int width = mdriver.manage().window().getSize().width;
		int height = mdriver.manage().window().getSize().height;

		for (int i = 1; i <= times; i++) {
			mdriver.swipe(width * 9 / 10, height / 2, width / 10, height / 2, 500);
			System.out.println("向左滑动次数：" + i);
			Thread.sleep(1000);
		}
	}

	public static void swipeUpUntilFind(String str){

		int width = mdriver.manage().window().getSize().width;
		int height = mdriver.manage().window().getSize().height;

		for (int i = 0; i < 5; i++) {
			mdriver.swipe(width / 2, height * 7 / 10, width / 2, height * 3 / 10, 500);
			if (mdriver.getPageSource().contains(str)) {
				break;
			}
		}

	}
	
	/**
	 * 截图，将图片放在test-output\\pics_actul中，用于和test-output\\pic_expected对比
	 * @param screenShotName
	 */
	public static void takeScreenShot(String screenShotName) {

		String path = System.getProperty("user.dir") + "\\test-output\\pics_actul";
		File screenShot = mdriver.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(screenShot, new File(path + "\\" + screenShotName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("截图成功："+screenShotName);
	}

}