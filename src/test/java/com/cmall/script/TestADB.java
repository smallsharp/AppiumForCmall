package com.cmall.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.testng.annotations.Test;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.InstallException;
import com.cmall.utils.DDMlibUtil;
import com.cmall.utils.LogUtil;

/**
 * ADB 相关脚本
 * 
 * @author cm
 *
 */
public class TestADB {

	LogUtil log = new LogUtil(TestADB.class);

	@Test(description = "批量安装Android应用")
	public void testInstall() {
		List<String> names = DDMlibUtil.getSerialNumber();
		log.info("检测到设备总数：" + names.size() + " 台");

		Runtime run = Runtime.getRuntime();
		for (String name : names) {
			try {
				log.info("开始对设备 " + name + " 推送配置文件");
				String configPath = "C:\\Users\\cm\\Desktop\\ConfigForTest_MY.xml";
				run.exec("adb -s " + name + " push " + configPath + " /sdcard ");

				String apkPath = "C:\\Users\\cm\\Desktop\\MeiYinDemo-v2.5.0.apk";
				run.exec("adb -s " + name + " install -r " + apkPath);
				log.info("推送成功");
			} catch (IOException e) {
				log.error("推送失败");
				e.printStackTrace();
			}
		}
	}

	@Test(description = "使用ddmlib安装")
	public void testInstall2() {
		
		List<IDevice> devices = DDMlibUtil.getIDeviceNames();
		System.out.println("一共检测到设备：" + devices.size() + " 台");

		for (IDevice device : devices) {
			try {
				device.installPackage("C:\\Users\\cm\\Desktop\\MeiYinDemo-v2.5.0.apk", true);
				System.out.println("success");
			} catch (InstallException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Test
	public void test() {

		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec("javac");
			InputStream input = proc.getErrorStream();
			InputStreamReader reader = new InputStreamReader(input, "GBK");
			BufferedReader br = new BufferedReader(reader);
			String line = null;
			StringBuffer sb = new StringBuffer();

			while ((line = br.readLine()) != null)
				sb.append(line + "\n");
			System.out.println(sb.toString());
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
