package top.base.utils;

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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class Assist {

	private static final Logger log = Logger.getLogger(Assist.class);
	private static AndroidDriver<WebElement> driver = Driver.newInstance();

	/**
	 * 
	 * @Description 动态等待activity出现
	 * @Data 2017年5月3日
	 * @return true or false
	 */
	public static boolean waitActivity(String activityName) {
		boolean flag = false;
		int i = 1;
		while (i <= 10) {
			try {
				if (activityName.contains(driver.currentActivity())) {
					log.info(activityName + " Found!");
					flag = true;
					break;
				} else {
					log.info(activityName + ",第" + i + "次等待，Not Found!");
					i++;
					Thread.sleep(2000);
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
				if ((element = driver.findElementById(resourceId)).isDisplayed()) {
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

	// webview页面切换时，需要加这个方法
	public String pageShift() {
		
		String currentHandle = driver.getWindowHandle();
		Set<String> allHandles = driver.getWindowHandles();

		for (String s : allHandles) {
			if (!s.equals(currentHandle)) {
				driver.switchTo().window(s);
			}
		}
		return currentHandle;
	}

	/***
	 * 切换WEB页面查找元素
	 */
	public static void switchtoWeb() {
		try {
			Set<String> contextNames = driver.getContextHandles();
			for (String contextName : contextNames) {
				// 用于返回被测app是NATIVE_APP还是WEBVIEW，如果两者都有就是混合型App
				if (contextName.contains("WEBVIEW") || contextName.contains("webview")) {
					driver.context(contextName);
					System.out.println("跳转到web页 开始操作web页面");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearText(WebElement element) {

		String text = element.getText();
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_MOVE_END);// 123

		for (int i = 0; i < text.length(); i++) {
			driver.pressKeyCode(AndroidKeyCode.KEYCODE_DEL);// 67
		}
	}

	/***
	 * 检查网络
	 * 
	 * @return 是否正常
	 */
	public boolean checkNet() {
		String text = driver.getConnection().toString();
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
		return driver.findElementByAndroidUIAutomator("new UiSelector().descriptionContains(\"" + name + "\")");
	}

	/***
	 * 根据UIautomator底层方法得到对应text的view
	 * 
	 * @param text名
	 * @return View
	 */
	public static WebElement getViewbyUitext(String name) {
		return driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + name + "\")");
	}

	public static String getScreen() {
		String fileRoute = "路径";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		String picname = fileRoute + df.format(new Date()).toString() + ".png";
		File screen = driver.getScreenshotAs(OutputType.FILE);
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
	public void slideUP() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		driver.swipe(x / 2, y / 3 * 2, x / 2, y / 3 * 1, 0);
	}

	/***
	 * 下滑1/4屏幕
	 */
	public void slideDown() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		driver.swipe(x / 2, y / 3 * 1, x / 2, y / 3 * 2, 0);
	}

	/***
	 * 左滑1/2屏幕
	 */
	public void slideLeft() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		driver.swipe(x / 4 * 3, y / 2, x / 4 * 1, y / 2, 0);
	}

	/***
	 * 右滑1/2屏幕
	 */
	public void slideRight() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		driver.swipe(x / 4 * 1, y / 2, x / 4 * 3, y / 2, 0);
	}

	/***
	 * 特殊上滑
	 * 
	 * @param 传入从左到右宽度的百分比(1-99之间)
	 */
	public void slideUP(int i) {
		Assert.assertFalse(i <= 0 || i >= 100, "上滑宽度传入错误");
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		driver.swipe(x / 10 * i, y / 3 * 2, x / 10 * i, y / 3 * 1, 0);
	}

	/***
	 * 特殊下滑
	 * 
	 * @param 传入从左到右宽度的百分比(1-99之间)
	 */
	public void slideDown(int i) {
		Assert.assertFalse(i <= 0 || i >= 100, "下滑宽度传入错误");
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		driver.swipe(x / 10 * i, y / 3 * 1, x / 10 * i, y / 3 * 2, 0);
	}

	/***
	 * 特殊左滑
	 * 
	 * @param 传入从上到下宽度的百分比(1-99之间)
	 */
	public void slideLeft(int i) {
		Assert.assertFalse(i <= 0 || i >= 100, "左滑宽度传入错误");
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		driver.swipe(x / 4 * 3, y / 10 * i, x / 4 * 2, y / 10 * i, 0);
	}

	/***
	 * 特殊右滑
	 * 
	 * @param 传入从上到下宽度的百分比(1-99之间)
	 */
	public void slideRight(int i) {
		Assert.assertFalse(i <= 0 || i >= 100, "左滑宽度传入错误");
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		driver.swipe(x / 4 * 2, y / 10 * i, x / 4 * 3, y / 10 * i, 0);
	}

	// 向左滑动
	public static void swipeToLeft(int times) throws InterruptedException {

		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		for (int i = 1; i <= times; i++) {
			driver.swipe(width * 9 / 10, height / 2, width / 10, height / 2, 500);
			System.out.println("向左滑动次数：" + i);
			Thread.sleep(1000);
		}
	}

	public static void swipeUpUntilFind(String str){

		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		for (int i = 0; i < 5; i++) {
			driver.swipe(width / 2, height * 7 / 10, width / 2, height * 3 / 10, 500);
			if (driver.getPageSource().contains(str)) {
				break;
			}
		}

	}

}