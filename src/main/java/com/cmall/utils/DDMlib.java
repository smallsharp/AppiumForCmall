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
	 * 如果没有设备连接，会返回null
	 * @return
	 */
	public IDevice getDevice() {
		AndroidDebugBridge adb = AndroidDebugBridge.createBridge();
		IDevice devices[] = null;
		IDevice device = null;
		if (waitForDevice(adb)) {
			devices = adb.getDevices();
			if (devices.length > 0) {
				device = devices[0];
			}else {
				return null;// 如果没有设备连接，会返回null
			}
		}
		return device;
	}

	/**
	 * 测试用
	 */
	@Test
	public void test() {
		
		DDMlib ddMlibUtil = DDMlib.getInstance();
		ddMlibUtil.init();
	
		IDevice device = ddMlibUtil.getDevice();
		
		if (device == null) {
			log.error("没有检测到Android设备！");
			return;
		}
		
		// 获取所有build.prop 所有配置信息
		Map<String, String> map = device.getProperties();
		
		for(String key:map.keySet()) {
			System.out.println(key + " --> "+map.get(key));
		}
		
		log.info(device.getProperty("dalvik.vm.heapgrowthlimit"));
		log.info(device.getProperty("dalvik.vm.heapstartsize"));
		log.info(device.getProperty("ro.product.name"));
		log.info(device.getProperty("ro.build.version.release"));
		log.info(device.getProperty("ro.serialno"));
		log.info(device.getProperty("ro.build.version.sdk"));
		log.info(device.getProperty("ro.product.cpu.abilist"));
		
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
