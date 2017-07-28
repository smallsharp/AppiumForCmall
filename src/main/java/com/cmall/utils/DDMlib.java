package com.cmall.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;

/**
 * 需要ddmlib.jar
 * 获取实例getInstance()
 * 使用前先 init(),使用完毕后需要finish()
 * @author lee
 *
 */
public class DDMlib {
	
	private LogUtil log = new LogUtil(DDMlibUtil.class);
	private static DDMlib instance;
	public static synchronized DDMlib getInstance() {
		if (instance == null) {
			instance = new DDMlib();
		}
		return instance;
	}
	
	private DDMlib() {
	}
	
	public void init() {
		AndroidDebugBridge.init(false);
	}

	public void finish() {
		AndroidDebugBridge.terminate();
	}

	/**
	 * 获取所有连接Android设备名称
	 * 
	 * @return
	 */
	public List<String> getSerialNumber() {
		List<String> list = new ArrayList<>();
		AndroidDebugBridge adb = null;
		if (adb == null) {
			adb = AndroidDebugBridge.createBridge();
		}
		if (waitForDevice(adb)) {
			IDevice[] devices = adb.getDevices();
			for (int i = 0; i < devices.length; i++) {
				list.add(devices[i].getSerialNumber());
			}
		}
		return list;
	}

	/**
	 * 获取设备：List<IDevice>
	 * 
	 * @return
	 */
	public List<IDevice> getIDeviceNames() {

		List<IDevice> list = new ArrayList<>();
		AndroidDebugBridge adb = null;
		if (adb == null) {
			adb = AndroidDebugBridge.createBridge();
		}
		if (waitForDevice(adb)) {
			IDevice[] devices = adb.getDevices();
			for (IDevice device : devices) {
				list.add(device);
			}
		}
		return list;
	}

	private boolean waitForDevice(AndroidDebugBridge adb) {

		int count = 0;
		while (!adb.hasInitialDeviceList()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
			if (count > 100) {
				System.err.print("[Time Out] No Devices Found !");
				return false;
			}
		}
		return true;
	}
	
	
	public void usingWaitLoop() {
		AndroidDebugBridge adb = AndroidDebugBridge.createBridge();
		try {
			int trials = 10;
			while (trials > 0) {
				Thread.sleep(50);
				if (adb.isConnected()) {
					break;
				}
				trials--;
			}

			if (!adb.isConnected()) {
				System.err.println("Couldn't connect to ADB server");
				return;
			}

			trials = 10;
			while (trials > 0) {
				Thread.sleep(50);
				if (adb.hasInitialDeviceList()) {
					break;
				}
				trials--;
			}

			if (!adb.hasInitialDeviceList()) {
				System.out.println("Couldn't list connected devices");
				return;
			}

			for (IDevice device : adb.getDevices()) {
				System.out.println("- " + device.getSerialNumber());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			AndroidDebugBridge.disconnectBridge();
		}
	}

	/**
	 * 获取第一台连接的Android设备
	 * 
	 * @return
	 */
	public IDevice getDevice() {
		AndroidDebugBridge adb = AndroidDebugBridge.createBridge();
		if (waitForDevice(adb)) {
			IDevice devices[] = adb.getDevices();
			if (devices.length > 0) {
				return devices[0];
			} else {
				System.out.println("没有检测到Android设备！");
			}
		}
		return null;
	}

	/**
	 * 测试用
	 */
	@Test
	public void test() {
		
		DDMlib ddMlibUtil = DDMlib.getInstance();
		ddMlibUtil.init();
	
		IDevice dev = ddMlibUtil.getDevice();
		
		Map<String, String> map = dev.getProperties();// 获取所有build.prop 所有配置信息
		
/*		for(String key:map.keySet()) {
			System.out.println(key + " --> "+map.get(key));
		}*/
		
		String heapGrowth = dev.getProperty("dalvik.vm.heapgrowthlimit");
		String heapStart = dev.getProperty("dalvik.vm.heapstartsize");
		String productName = dev.getProperty("ro.product.name");
		String version = dev.getProperty("ro.build.version.release");
		String serialno = dev.getProperty("ro.serialno");
		String sdkVersion = dev.getProperty("ro.build.version.sdk");
		String cpu = dev.getProperty("ro.product.cpu.abilist");
		
		log.info(heapGrowth);
		log.info(heapStart);
		log.info(productName);
		log.info(version);
		log.info(serialno);
		log.info(sdkVersion);
		log.info(cpu);
		
/*		List<String> devices = getSerialNumber();
		for (String device : devices) {
			System.out.println(device);
		}

		List<IDevice> devices2 = getIDeviceNames();
		for (IDevice device : devices2) {
			System.out.println(device);
			System.out.println(device.getState());
			System.out.println(device.isBootLoader());
		}*/
		ddMlibUtil.finish();
	}

}
