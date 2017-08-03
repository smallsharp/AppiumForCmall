package com.cmall.appium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

/**
 * Appium 服务端
 * 
 * @author cm
 *
 */
public class AppiumServer {

	Logger log = Logger.getLogger(AppiumServer.class);
	Process proc = null;

	public AppiumServer() {
		killTask("node.exe");
		log.info("kill node.exe and produce a new appiumServer");
	}

	/**
	 * 
	 * @param port
	 *            默认4723，--bootstrap-port 默认4724，--chromedriver-port：默认9515
	 * @param deviceName
	 */
	public void startServer(String ip, int port, String deviceName) {

		String kill = "taskkill /F /im node.exe";
		runCommand2(kill);

		log.info("start to launch server on " + ip + " " + port + " " + deviceName);
		int bpport = port + 1;
		int chromeport = port + 4792;
		String launch = "appium.cmd -a " + ip + " -p " + port + " -bp " + bpport
				+ " --session-override --chromedriver-port " + chromeport + " -U " + deviceName + " >c://" + deviceName
				+ ".txt";
		log.info(launch);
		try {
			proc = runCommand2(launch);
			InputStream input = proc.getInputStream();
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(reader);
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			int exitVal = 100;
			exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Appium Server is running on "+ip+" "+port+" "+deviceName);
	}

	/**
	 * 停止服务
	 */
	public void stopServer() {
		// killTask("node.exe");
		if (proc != null) {
			proc.destroy();
		}
	}

	/**
	 * kill 进程
	 * 
	 * @param taskname
	 */
	private void killTask(String taskname) {
		String cmd = "taskkill /F /im " + taskname;
		runCommand(cmd);
	}

	/**
	 * 执行外部命令
	 * 
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
	 * 
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
	@SuppressWarnings("unused")
	private void getRuntimeData(String command) {
		Process process = runCommand2(command);
		InputStream inputStream = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String line;
		StringBuffer sb = new StringBuffer();
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
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

	public void test() {

//		String find = "tasklist | findstr node.exe";
		String kill = "taskkill /F /im node.exe";
		// getRuntimeData(find);
		String launchServer = "appium.cmd -a " + "127.0.0.1" + " -p " + 4723 + " -bp " + 4724
				+ " --session-override --chromedriver-port " + 9515 + " -U " + "80c20e99" + " >c://" + "80c20e99"
				+ ".txt";

		runCommand2(kill);
		Process proc = runCommand2(launchServer);
		InputStream input = proc.getInputStream();
		InputStreamReader reader = new InputStreamReader(input);
		BufferedReader br = new BufferedReader(reader);
		String line = null;
		System.out.println("~~~~");
		try {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("");
			int exitVal = 100;
			exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
