package top.base.appium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class AppiumServer {

	Logger log = Logger.getLogger(AppiumServer.class);

	public AppiumServer() {
//		killTask("node.exe");
	}

	/**
	 * 
	 * @param port 默认4723，--bootstrap-port 默认4724，--chromedriver-port：默认9515
	 * @param udid
	 */
	public void runServer(int port, String udid) {
		int bpport = port + 1;
		int chromeport = port + 4792;
		// 多设备server端需要手动指定每台设备的udid,安卓无线连接下就是设备的ip:port..
		String cmd = "appium.cmd -p " + port + " -bp " + bpport + " --session-override --chromedriver-port "
				+ chromeport + " -U " + udid + " >c://" + port + ".txt";
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			int value= process.waitFor();
//			System.out.println("process.waitFor():"+value);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(" Appium Server in running :" + udid +" "+ port);

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
	private void getRuntimeData(){
		
		Process process = runCommand2("adb devices");
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
	
	public static void main(String[] args) {
		AppiumServer as = new AppiumServer();
		as.getRuntimeData();
	}

}
