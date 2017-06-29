package com.cmall.appium;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.cmall.play.pages.Play_ActivityList;
import com.cmall.utils.LogUtil;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

/**
 * 静态方法 可以直接使用
 * @author cm
 *
 */
public class Helper {
	
	static LogUtil log = new LogUtil(Helper.class);
	private static AndroidDriver<MobileElement> mdriver;
	public Helper(AndroidDriver<MobileElement> mdriver){
		Helper.mdriver = mdriver;
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
		
		log.info("\n"+"[Activity] Waiting activity ==> " + "(" + activityName + ")");
		try {
			for (int i = 0; i < 10; i++) {
				Thread.sleep(500);
				if (activityName.contains(mdriver.currentActivity())) {
					log.info("\n" + "[Activity] Found activity ==> " + "(" + activityName + ")");
					return true;
				}
			}
			Thread.sleep(500);
			log.info("\n"+"[Activity] ActivityNotFound:" + "(" + activityName + ")" +"\n"+"CurrentActivity is:" + "(" + mdriver.currentActivity() + ")");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 
	 * @param element
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean waitElement(MobileElement element)  {
		
		for (int j = 0; j < 3; j++) {

			log.info("\n" + "[Element] Waiting element ==> " + "(" + splitElement(element) + ")");

			if (element.isDisplayed()) {
				log.info("\n"+"[Element] Found element ==> " +  "(" + splitElement(element) + ")");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		log.info("\n" + "[Element] ElementNotFound ==> " + "(" + splitElement(element) + ")");
		return false;
	}
	
	/**
	 * 
	 * @param activityName
	 * @param element
	 * @return
	 * 
	 */
	public static boolean waitElement(String activityName,MobileElement element) {
		
		if (waitActivity(activityName)) {
			
			if (waitElement(element)) {
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
		log.info("\n"+"[Element] click on element ==> " + "(" + splitElement(element) + ")" );
		element.click();
	}
	
	/**
	 * 输入
	 * @param element
	 * @param text
	 */
	public static void inputText(MobileElement element,CharSequence... text){
		log.info("\n"+"[Element] input text ==> " + "(" + splitElement(element) + ")");
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
	public static void context_to_webview() {

		Set<String> ContextHandles = mdriver.getContextHandles();
		log.info("\n"+"All ContextHandles :" + ContextHandles);
		for (String contextName : ContextHandles) {
			if (contextName.contains("WEBVIEW") || contextName.contains("webview")) {
				mdriver.context(contextName);
				log.info("\n"+"[Webview] context_to_webview success :" + contextName);
				break;
			}
		}
	}
	
	public static void context_to_native(){
		mdriver.context("NATIVE_APP");
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
	public static MobileElement findElementByDesc(String name) {
		return mdriver.findElementByAndroidUIAutomator("new UiSelector().descriptionContains(\"" + name + "\")");
	}

	/***
	 * 根据UIautomator底层方法得到对应text的view
	 * 
	 * @param text名
	 * @return View
	 */
	public static MobileElement findElementByText(String name) {
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

	/**
	 * 向上滑动1/3屏幕高度
	 */
	public static void swipeUp() {

		int width = mdriver.manage().window().getSize().width;
		int height = mdriver.manage().window().getSize().height;
		mdriver.swipe(width / 2, height * 2 / 3, width / 2, height * 1 / 3, 1000);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 滑动到指定元素高度并点击，需要在webview下执行
	 * @param element
	 */
	public static void scrollAndClick(MobileElement element) {
		int elementPosition = element.getLocation().getY();
		String js = String.format("window.scroll(0, %s)", elementPosition);
		((JavascriptExecutor) mdriver).executeScript(js);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		element.click();
	}
	
	/**
	 * 滑动到指定元素高度，需要在webview下执行
	 * @param element
	 */
	public static void scrollToElement(MobileElement element) {
		
		int elementPosition = element.getLocation().getY();
		String js = String.format("window.scroll(0, %s)", elementPosition);
		mdriver.executeScript(js);
	}

	/**
	 * 回到主页，适用：Play
	 */
	public static void backToHomeActivity() {
		
		try {
			
			if (Play_ActivityList.HOME_ACTIVITY.equals(mdriver.currentActivity())) {
				return;
			}
			log.info("\n" + "Run：backToHomeActivity");
			for (int i = 0; i < 6; i++) {
				
				if (Play_ActivityList.HOME_ACTIVITY.equals(mdriver.currentActivity())) {
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
	
	/**
	 * 返回指定页面
	 */
	public static void backToActivity(String ActivityName) {
		
		try {
			if (ActivityName.contains(mdriver.currentActivity())) {
				return;
			}
			
			for (int i = 0; i < 6; i++) {
				if (ActivityName.contains(mdriver.currentActivity())) {
					return;
				}
				Thread.sleep(2500);
				pressKeyCode(AndroidKeyCode.BACK);
			}

		} catch (InterruptedException e) {
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
	
	public static void tap_StartBuilt(){
		
		int width = mdriver.manage().window().getSize().width;
		int height = mdriver.manage().window().getSize().height;
		System.out.println("width:"+width);
		System.out.println("height:"+height);

		if (height==1280) {
			mdriver.tap(1, width / 2, height / 1280 * 1250, 500);
			return;
		}
		
		if (height==1776) {
			mdriver.tap(1, width / 2, height / 1776 * 1710, 500);
			return;
		}
		
		if (height==1920) {
			mdriver.tap(1, width / 2, height / 1920 * 1880, 500);
			return;
		}
	}
	
	public static void tap_White(){
		
		int width = mdriver.manage().window().getSize().width;
		int height = mdriver.manage().window().getSize().height;
		if (height==1280) {
			mdriver.tap(1, width / 720 * 92, height / 1280 * 850, 500);
			return;
		}
		
		if (height==1920) {
			mdriver.tap(1, width / 100 * 13, height / 80 * 53, 500);
			return;
		}
		if (height==1776 && width==1080) {
			mdriver.tap(1, width / 1080 * 138, height / 1776 * 1126, 500);
			return;
		}
	}
	
	public static void tap_S(){
		
		int width = mdriver.manage().window().getSize().width;
		int height = mdriver.manage().window().getSize().height;
		
		if (height==1280) {
			mdriver.tap(1, width / 720 * 92, height / 1280 * 980, 500);
			return;
		}
		
		if (height==1920) {
			mdriver.tap(1, width / 100 * 13, height / 30 * 23, 500);
			return;
		}
		
		if (height==1776 && width==1080) {
			mdriver.tap(1, width / 1080 * 138, height / 1776 * 1328, 500);
			return;
		}
	}
	
	public static boolean isPageLoaded(){
		JavascriptExecutor jsExecutor = (JavascriptExecutor) mdriver;
		String status = (String) jsExecutor.executeScript("var status=document.readyState;return status");
		if (status.contains("complete")) {
			return true;
		}
		
		int i = 0;
		while (!status.contains("complete")) {
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i>10) {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 等待
	 * @param millis
	 */
	public static void pause(long millis){
		log.info("执行了等待");
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 等待
	 * @param millis
	 */
	public static void pause_default_time(){
		pause(2000);
	}
	
	public static int getDeviceWidth(){
		int width = mdriver.manage().window().getSize().width;
		return width;
	}
	
	public static int getDeviceHeight(){
		int height = mdriver.manage().window().getSize().height;
		return height;
	}
	
	static IDevice device;
	private static IDevice getDevice() {
		
		AndroidDebugBridge.init(false);
		AndroidDebugBridge bridge = AndroidDebugBridge.createBridge();
		try {
			Thread.sleep(1000);
			//Calling getDevices() right after createBridge(String, boolean) will generally result in an empty list.
			if (bridge.hasInitialDeviceList()) {
				IDevice devices[] = bridge.getDevices();
				if (devices.length >= 0) {
					device = devices[0];
				}
				return device;
			}
			System.out.println("没有检测到设备");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public static String getDeviceName(){
		
		if (device == null) {
			device = getDevice();
		}
		String brand =device.getProperty("ro.product.brand");
		String name = device.getSerialNumber();
		return brand +"_"+ name;
	}
	
	
}