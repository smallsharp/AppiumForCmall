package com.cmall.utils;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;

/**
 * 需要ddmlib.jar
 * 
 * @author lee
 *
 */
public class DDMlib {
	
//	private static AndroidDebugBridge adb;
	
	private static DDMlib instance;
	public static synchronized DDMlib getInstance() {
		if (instance == null) {
			instance = new DDMlib();
		}
		return instance;
	}
	
	private DDMlib() {
		// TODO Auto-generated constructor stub
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

	/**
	 * it should be run firstly
	 * 
	 * @param
	 * @return
	 */
	private boolean waitForDevice(AndroidDebugBridge bridge) {

		if (bridge == null) {
			bridge = AndroidDebugBridge.createBridge();
		}
		int count = 0;
		while (!bridge.hasInitialDeviceList()) {
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
	
	
	public void usingWaitLoop() throws Exception {
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
		} finally {
			AndroidDebugBridge.disconnectBridge();
		}
	}

	/**
	 * 获取第一台连接的Android设备
	 * 
	 * @return
	 */
	public IDevice getFirstDevice() {
		AndroidDebugBridge adb = null;
		if (adb == null) {
			adb = AndroidDebugBridge.createBridge();
		}
		if (waitForDevice(adb)) {
			IDevice devices[] = adb.getDevices();
			if (devices.length > 0) {
				return devices[0];
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
		
		String name = ddMlibUtil.getFirstDevice().getSerialNumber();
		System.out.println(name);
		
		List<String> devices = getSerialNumber();
		for (String device : devices) {
			System.out.println(device);
		}

		List<IDevice> devices2 = getIDeviceNames();
		for (IDevice device : devices2) {
			System.out.println(device);
			System.out.println(device.getState());
			System.out.println(device.isBootLoader());
		}
		
		ddMlibUtil.finish();

	}


}
