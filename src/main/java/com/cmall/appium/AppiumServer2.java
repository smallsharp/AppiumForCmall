package com.cmall.appium;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.cmall.utils.LogUtil;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;

/**
 * Appium Server 端相关API
 * 经测试 AppiumServer不支持多设备
 * @author lee
 *
 */
public class AppiumServer2 {

	private AppiumDriverLocalService appiumService;
	private LogUtil log = new LogUtil(AppiumServer2.class);

	public AppiumServer2() {
	}
	

	/**
	 * 启动AppiumServer
	 * @param ipAddress
	 * @param port
	 * @param devicesName
	 */
	public void startServer(String ipAddress, int port,String devicesName) {
		File folder = new File("log");
		File logFile = null;
		try {
			folder.mkdir();// 建立目录
			logFile = new File(folder, Thread.currentThread().getName()+System.currentTimeMillis() + ".log");
			logFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
/*		Map<String, String> map = new HashMap<>();
		map.put("-U ", devicesName);
		map.put("-S", "--session-override");
		serviceBuilder.withEnvironment(map);*/
		serviceBuilder.withLogFile(logFile);
		serviceBuilder.withIPAddress(ipAddress);
		serviceBuilder.usingPort(port);
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("deviceName", devicesName);
		serviceBuilder.withCapabilities(dc);
		appiumService = AppiumDriverLocalService.buildService(serviceBuilder);
		
		if (appiumService == null || !appiumService.isRunning()) {
			appiumService.start();
		}
		log.info("AppiumServer is started with " + ipAddress + " " + port +" "+devicesName);
	}

	/**
	 * 开启默认的AppiumServer，默认使用4723端口
	 */
	public void startDefaultService() {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		appiumService = AppiumDriverLocalService.buildService(builder);
//		appiumService = AppiumDriverLocalService.buildDefaultService();
		// 同上两行的效果一样，默认使用4723端口
		appiumService.start();
		if (appiumService == null || !appiumService.isRunning()) {
			throw new RuntimeException("An appium server node is not started!");
		}
	}

	/**
	 * 
	 * @param ipAddress
	 * @param port
	 * @param capabilities
	 */
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

	/**
	 * 
	 * @param ipAddress
	 * @param port
	 * @param logFile
	 * @param arguments
	 */
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

	/**
	 * 关闭服务
	 */
	public void stopServer() {
		if (appiumService != null) {
			appiumService.stop();
			log.info("AppiumServer has stoped");
		}
	}

}
