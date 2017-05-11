package top.base.listener;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.security.Credentials;
import io.appium.java_client.events.api.general.AlertEventListener;

public class MyAlertEventListener implements AlertEventListener {

	@Override
	public void beforeAlertAccept(WebDriver driver, Alert alert) {
		// TODO Auto-generated method stub
		System.out.println("beforeAlertAccept");
		
	}

	@Override
	public void afterAlertAccept(WebDriver driver, Alert alert) {
		// TODO Auto-generated method stub
		System.out.println("afterAlertAccept");
		
	}

	@Override
	public void afterAlertDismiss(WebDriver driver, Alert alert) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver, Alert alert) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertSendKeys(WebDriver driver, Alert alert, String keys) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertSendKeys(WebDriver driver, Alert alert, String keys) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAuthentication(WebDriver driver, Alert alert, Credentials credentials) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAuthentication(WebDriver driver, Alert alert, Credentials credentials) {
		// TODO Auto-generated method stub
		
	}

}
