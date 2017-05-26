package top.base.appium;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import io.appium.java_client.events.api.general.ElementEventListener;

public class MyElementEventListener implements ElementEventListener {
	
	Logger log = Logger.getLogger(MyElementEventListener.class);

	public void beforeClickOn(WebElement element, WebDriver driver) {
//		Reporter.log("@准备点击:" + splitElement(element),true);
//		Reporter.log("<br>",false);
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		Reporter.log("@开始点击:" + splitElement(element),true);
		Reporter.log("<br>",false);
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
//		Reporter.log("@准备改变:" +splitElement(element)+"的数值",true);
//		Reporter.log("<br>",false);
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		Reporter.log("@控件" + splitElement(element) + "的数值已改变",true);
		Reporter.log("<br>",false);

	}

	// 获取操作的控件字符串
	private String splitElement(WebElement element) {
		// 用"->"分割，分成数组，取下标为1的
		// [[MyAndroidDriver:  on LINUX (750e968d-5203-408c-9407-cf695a5eb436)] -> id: com.tude.android:id/btn_jump]
		String str = element.toString().split("-> ")[1];
		return str.substring(0, str.length() - 1);
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}
}