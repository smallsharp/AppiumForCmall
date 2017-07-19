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
public class DDMlibUtil2 {

	private static IDevice device;
	
	public static IDevice getDevice() {
		return device;
	}

	public void setDevice(IDevice device) {
		this.device = device;
	}

	private static AndroidDebugBridge bridge;

	public static AndroidDebugBridge getBridge() {
		return bridge;
	}

	public static void setBridge(AndroidDebugBridge bridge) {
		DDMlibUtil2.bridge = bridge;
	}

	/**
	 * 获取所有连接Android设备名称
	 * 
	 * @return
	 */
	public static List<String> getDeviceNames() {
		AndroidDebugBridge.terminate();
		AndroidDebugBridge.init(false);
		List<String> list = new ArrayList<>();
		if (bridge == null) {
			bridge = AndroidDebugBridge.createBridge();
		}
		if (waitForDevice(bridge)) {
			IDevice[] devices = bridge.getDevices();
			for (int i = 0; i < devices.length; i++) {
				list.add(devices[i].getSerialNumber());
			}
		}
		return list;
	}
	
	public static List<IDevice> getIDeviceNames() {
		AndroidDebugBridge.terminate();
		AndroidDebugBridge.init(false);
		IDevice[] devices = null;
		List<IDevice> list = new ArrayList<>();
		if (bridge == null) {
			bridge = AndroidDebugBridge.createBridge();
		}
		if (waitForDevice(bridge)) {
			devices = bridge.getDevices();
			for(IDevice device:devices) {
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
	private static boolean waitForDevice(AndroidDebugBridge bridge) {

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

	/**
	 * 获取第一台连接的Android设备
	 * 
	 * @return
	 */
	public static IDevice getFirstDevice() {

		if (waitForDevice(bridge)) {
			IDevice devices[] = bridge.getDevices();
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
/*		List<String> devices = getDeviceNames();
		for (String device : devices) {
			System.out.println(device);
		}*/
		
		List<IDevice> devices2 = getIDeviceNames();
		for(IDevice device:devices2) {
			System.out.println(device);
			System.out.println(device.getState());
		}
	}


}
