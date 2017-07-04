package com.cmall.appium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class AppiumServer {

	Logger log = Logger.getLogger(AppiumServer.class);

	public AppiumServer() {
		killTask("node.exe");
		log.info("a new appiumServer");
	}

	/**
	 * 
	 * @param port 默认4723，--bootstrap-port 默认4724，--chromedriver-port：默认9515
	 * @param deviceName
	 */
	public void startServer(int port, String deviceName) {
		
		log.info("startServer with " + deviceName +" "+ port);
		int bpport = port + 1;
		int chromeport = port + 4792;
		String ip = "127.0.0.1";
		String cmd = "appium.cmd -a "+ ip +" -p " + port + " -bp " + bpport + " --session-override --chromedriver-port "
				+ chromeport + " -U " + deviceName + " >c://" + port + ".txt";
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			Thread.sleep(3000);
//			process.waitFor();
//			int value= process.waitFor();
//			System.out.println("process.waitFor():"+value);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("Appium Server in running on " + deviceName +" "+ port);
	}
	
	/**
	 * 停止服务
	 */
	public void stopServer() {
		killTask("node.exe");
	}
	
	/**
	 * kill 进程
	 * @param taskname
	 */
	private void killTask(String taskname) {
		String cmd = "taskkill /F /im " + taskname;
		runCommand(cmd);
	}

	/**
	 * 执行外部命令
	 * @param command
	 */
	private void runCommand(String command) {
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行外部命令
	 * @param command
	 */
	private Process runCommand2(String command) {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return process;
	}
	
	/**
	 * 读取数据
	 */
	private void getRuntimeData(String command){
		Process process = runCommand2(command);
		InputStream inputStream = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		String line;
		StringBuffer sb = new StringBuffer();
		try {
			while ((line = reader.readLine()) != null) {
			sb.append(line+"\n");	
			process.waitFor();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(sb);
			try {
				inputStream.close();
				reader.close();
				process.destroy();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		AppiumServer as = new AppiumServer();
		as.getRuntimeData("adb devices");
	}

}
