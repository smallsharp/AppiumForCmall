package com.cmall.script;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import com.cmall.utils.ExcelReader;
import com.cmall.utils.LogUtil;
import jxl.write.WriteException;

/**
 * Android 性能测试
 * @author cm
 *
 */
public class Performance {
	LogUtil log = new LogUtil(Performance.class);
	// 获取应用内存使用情况
	private static String memUsage_cmd = "adb shell dumpsys meminfo ";

	// 应用最大内存限制
	private static String heaplimit = "adb shell getprop|findstr heapgrowthlimit";

	// 获取设备总内存等信息
	private static String memTotal_cmd = "adb shell cat /proc/meminfo";

	// 获取应用CPU使用情况
	private static String cup_info = "adb shell top -n 1 -d 1 -s cpu |grep ";

	// 测试应用的packageName
	private static String packageName = "com.play.android";
	
	private DecimalFormat df = new DecimalFormat("######0.00");


	// No process found for: com.play.android

	/**
	 * 应用已经使用的PSS内存 单位：KB
	 * 
	 * @param packageName
	 * @return
	 */
	public static double getUsageMemory(String packageName) {
		String meminfo = getResponse(memUsage_cmd, packageName);
		String str = meminfo.substring(meminfo.indexOf("TOTAL", 0), meminfo.indexOf("Objects", 0));
		// TOTAL 59015 59200 8684 0 46604 38034 7985
		String arr[] = str.split(" ");
		List<String> list = new ArrayList<>();
		for (String s : arr) {
			if (s.length() > 0) {
				list.add(s);
			}
		}
		// KB 转化为 M
		return Double.valueOf(list.get(1)) / 1024;
	}

	public static double getTotalMemory(String packageName) {
		String meminfo = getResponse(memTotal_cmd);
		// MemTotal: 3867416 kB
		String total = meminfo.substring(meminfo.indexOf("MemTotal:"), meminfo.indexOf("kB"));
		return Double.valueOf(total.split(":")[1].trim()) / 1024;
	}

	/**
	 * Java堆内存使用 单位M
	 * 
	 * @param packageName
	 * @return
	 */
	public static double getHeapMemory(String packageName) {
		String meminfo = getResponse(memUsage_cmd, packageName);
		// Dalvik Heap 16530 32816 0 0 32324 30305 2019
		String str = meminfo.substring(meminfo.indexOf("Dalvik Heap"), meminfo.indexOf("Dalvik Other"));
		// System.out.println("str:"+str);
		String arr[] = str.split(" ");
		List<String> list = new ArrayList<>();
		for (String s : arr) {
			if (s.length() > 0) {
				list.add(s);
			}
		}
		return Double.valueOf(list.get(6)) / 1024;
	}

	/**
	 * adb shell top -n 1 -s cpu |findstr com.meitu.wheecam
	 * 
	 * @param packageName
	 * @return
	 */
	public static double getCpuinfo(String packageName) {
		String str = getResponse(cup_info, packageName);
		// 21823 0 0% S 116 1948508K 197952K fg u0_a59 com.meitu.wheecam
		return Double.valueOf(str.substring(str.indexOf("%") - 3, str.indexOf("%")).trim());
	}

	/**
	 * 内存的基本使用情况 adb shell dumpsys meminfo com.meitu.wheecam
	 * 
	 * @param packageName
	 * @return
	 */
	private static String getResponse(String cmd, String packageName) {
		Runtime runtime = Runtime.getRuntime();
		Process pro = null;
		StringBuffer sb = new StringBuffer();
		try {
			pro = runtime.exec(cmd + packageName);
			if (pro.waitFor() != 0) {
				System.err.println("exit value = " + pro.exitValue());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 执行外部命令，获取返回值
	 * 
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

	/**
	 * 内存使用情况
	 */
	public void getMemInfo() {

		double meminfo = getUsageMemory(packageName);
		log.info("PSS:" + df.format(meminfo) + " M");

		double totolMem = getTotalMemory(packageName);
//		log.info("手机总内存：" + df.format(totolMem) + " M");

		double memrate = meminfo / totolMem;
		log.info("内存使用率：" + df.format((memrate * 100)) + "%");
		
		double heap = getHeapMemory(packageName);
		log.info("Dalvik Heap:" + df.format(heap) + " M");
		
		log.info("Dalvik Heap 使用率："+ df.format(heap / 192 * 100) +"%");
	}

	/**
	 * cpu使用率
	 */
	public void getCpuInfo() {
		double cpuInfo = getCpuinfo(packageName);
		log.info("Cpu:" + cpuInfo + "%");
	}
	
	static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<String> getPerformance(){
		
		List<String> list = new ArrayList<>();
		String time = dateformat.format(System.currentTimeMillis());
		double meminfo = getUsageMemory(packageName);
		double totolMem = getTotalMemory(packageName);
		double memrate = meminfo / totolMem * 100;
		double cpuInfo = getCpuinfo(packageName);
		String usageMem_s = df.format(meminfo);
		String memrate_s = df.format(memrate);
		String cpuInfo_s = df.format(cpuInfo);
		
/*		String arr[] = new String[4];
		arr[0] = time;
		arr[1] = usageMem_s;
		arr[2] = memrate_s;
		arr[3] = cpuInfo_s;*/
		
		list.add(time);
		list.add(usageMem_s);
		list.add(memrate_s);
		list.add(cpuInfo_s);
		return list;
	}
	
	@Test
	public void test() {

		ExcelReader excel = new ExcelReader();
		
		String path = "D:\\workspace\\AppiumForCmall\\assets\\data2.xls";

		File file = new File(path);
		try {
			excel.writeToExcel(file, "Play", 2, 2000);
		} catch (WriteException | IOException e) {
			e.printStackTrace();
		}
	}

}
