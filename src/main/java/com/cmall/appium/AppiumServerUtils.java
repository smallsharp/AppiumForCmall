package com.cmall.appium;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;

/**
 * Appium Server 端相关API
 * @author lee
 *
 */
public class AppiumServerUtils {
	
	private static AppiumServerUtils appiumServerUtils;
	
	public static AppiumServerUtils getInstance() {
		if (appiumServerUtils == null) {
			synchronized (AppiumServerUtils.class) {
				if (appiumServerUtils == null) {
					appiumServerUtils = new AppiumServerUtils();
				}

			}
		}
		return appiumServerUtils;
	}

	
	private  AppiumDriverLocalService appiumService;
	
	public  AppiumDriverLocalService getService() {
		return appiumService;
	}


	/**
	 * 开启默认的AppiumServer，默认使用4723端口
	 */
	public void startDefaultService() {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		appiumService = AppiumDriverLocalService.buildService(builder);
//		appiumService = AppiumDriverLocalService.buildDefaultService(); 通上两行的效果一样，默认使用4723端口
		appiumService.start();
		if (appiumService == null || !appiumService.isRunning()) {
			throw new RuntimeException("An appium server node is not started!");
		}
	}

	/**
	 * 启动AppiumServer
	 * @param ipAddress
	 * @param port
	 */
	public void startServer(String ipAddress, int port) {
		
		File logFile = null;
		try {
			File folder = new File("log");
			folder.mkdir();// 建立目录
			logFile = new File(folder,System.currentTimeMillis()+".log");
			logFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress(ipAddress);
		builder.usingPort(port);
		builder.withLogFile(logFile);
		appiumService = AppiumDriverLocalService.buildService(builder);
		if (appiumService == null || !appiumService.isRunning()) {
			appiumService.start();
//			throw new RuntimeException("An appium server node is not started!");
		}
		
		System.out.println("AppiumServer has started");

	}
	
	public void startServer(String ipAddress, int port, DesiredCapabilities capabilities) {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress(ipAddress);
		builder.usingPort(port);
		if (capabilities != null) {
			builder.withCapabilities(capabilities);
		}
		appiumService = AppiumDriverLocalService.buildService(builder);
		appiumService.start();
		if (appiumService == null || !appiumService.isRunning()) {
			throw new RuntimeException("An appium server node is not started!");
		}

	}
	
	public void startServer(String ipAddress, int port, File logFile, ServerArgument... arguments) {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress(ipAddress);
		builder.usingPort(port);
		builder.withLogFile(logFile);
		for (ServerArgument argument : arguments) {
			builder.withArgument(argument);
		}
		appiumService = AppiumDriverLocalService.buildService(builder);
		appiumService.start();
		if (appiumService == null || !appiumService.isRunning()) {
			throw new RuntimeException("An appium server node is not started!");
		}

	}
	
	public void stopServer() {
		
		if (appiumService != null) {
			appiumService.stop();
			System.out.println("AppiumServer has stoped");
		}
	}

}
