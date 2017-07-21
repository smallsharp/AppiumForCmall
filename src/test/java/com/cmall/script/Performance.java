package com.cmall.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.testng.annotations.Test;

public class Performance {

	private static String memUsage_cmd = "adb shell dumpsys meminfo ";
	private static String memTotal_cmd = "adb shell cat /proc/meminfo";
	private static String cup_info = "adb shell top -n 1  -s cpu |grep ";

	/**
	 * 应用已经使用的PSS内存 单位：KB
	 * 
	 * @param packageName
	 * @return
	 */
	public static String getUsageMemory(String packageName) {
		String meminfo = getResponse(memUsage_cmd, packageName);
		String str = meminfo.substring(meminfo.indexOf("TOTAL:", 1), meminfo.indexOf("TOTAL SWAP"));
		// TOTAL: 99090
		return str.split(":")[1].trim();
	}
	
	
	public static String getTotalMemory(String packageName) {
		String meminfo = getResponse(memTotal_cmd);
		//MemTotal:        3867416 kB
		String total = meminfo.substring(meminfo.indexOf("MemTotal:"),meminfo.indexOf("kB"));
		return total.split(":")[1].trim();
	}

	/**
	 * Java堆内存使用 单位KB
	 * 
	 * @param packageName
	 * @return
	 */
	public static String getHeapMemory(String packageName) {
		String meminfo = getResponse(memUsage_cmd, packageName);
		String str = meminfo.substring(meminfo.indexOf("Java Heap:"), meminfo.indexOf("Native Heap:"));
		// Java Heap: 6340
		return str.split(":")[1].trim();
	}

	/**
	 * adb shell top -n 1  -s cpu |findstr com.meitu.wheecam
	 * @param packageName
	 * @return
	 */
	public static String getCpuinfo(String packageName) {
		String str = getResponse(cup_info, packageName);
		// 21823  0   0% S   116 1948508K 197952K  fg u0_a59   com.meitu.wheecam
		return str.substring(str.indexOf("%")-3,str.indexOf("%")).trim();
	}
	
	/**
	 * 内存的基本使用情况 adb shell dumpsys meminfo com.meitu.wheecam
	 * 
	 * @param packageName
	 * @return
	 */
	private static String getResponse(String cmd, String packageName) {
		String meminfo = "";
		Runtime runtime = Runtime.getRuntime();
		Process pro = null;
		try {
			pro = runtime.exec(cmd + packageName);
			if (pro.waitFor() != 0) {
				System.err.println("exit value = " + pro.exitValue());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line + "\n");
			}
			meminfo = sb.toString();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return meminfo;
	}
	
	/**
	 * 执行外部命令，获取返回值
	 * @param cmd
	 * @return
	 */
	private static String getResponse(String cmd) {
		String response = "";
		Runtime runtime = Runtime.getRuntime();
		Process pro = null;
		try {
			pro = runtime.exec(cmd);
			if (pro.waitFor() != 0) {
				System.err.println("exit value = " + pro.exitValue());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line + "\n");
			}
			response = sb.toString();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return response;
	}

	@Test
	public void test() {
		String packageName = "com.meitu.wheecam";
		String meminfo = getUsageMemory(packageName);
		System.out.println("PSS:" + meminfo + " KB");
		
		String totolMem = getTotalMemory(packageName);
		System.out.println(totolMem);
		double memrate = Integer.valueOf(meminfo)/Integer.valueOf(totolMem);
		
		System.out.println("内存使用占比：" + memrate);

		String heapMeminfo = getHeapMemory(packageName);
		System.out.println("Java Heap:" + heapMeminfo + " KB");
		
		String cpuInfo = getCpuinfo(packageName); 
		System.out.println("Cpu:"+cpuInfo);
	}

}
