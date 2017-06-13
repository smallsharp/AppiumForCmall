package top.base.appium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import io.appium.java_client.events.api.general.AppiumWebDriverEventListener;

public class MyAppiumListener implements AppiumWebDriverEventListener {

	// 获取操作的控件字符串
	private String splitElement(WebElement element) {
		// 用"->"分割，分成数组，取下标为1的
		// [[MyAndroidDriver:  on LINUX (750e968d-5203-408c-9407-cf695a5eb436)] -> id: com.tude.android:id/btn_jump]
		String str = element.toString().split("-> ")[1];
		return str.substring(0, str.length() - 1);
	}
	
	
	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		Reporter.log("click--> " + splitElement(element),true);

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		Reporter.log(splitElement(element)+"：控件内容已经改变",true);

	}


	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}



}
