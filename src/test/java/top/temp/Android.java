package top.temp;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;

/**
 * 需要ddmlib.jar
 * 
 * @author Administrator
 *
 */

public class Android {

	public static IDevice device;

	public static void main(String[] args) {

		AndroidDebugBridge.init(false); // 很重要
		device = getDevice();
		System.out.println(device.getSerialNumber());
		System.out.println(device.isOnline());
		System.out.println(device.isOffline());
		


	}

	private static IDevice getDevice() {
		IDevice device = null;
		AndroidDebugBridge bridge = AndroidDebugBridge.createBridge();
		waitDevicesList(bridge);
		try {
			
			IDevice devices[] = bridge.getDevices();
			if (devices.length>=0) {
				device = devices[0];
			}

		} catch (Exception e) {
			System.out.println("没有检测到Android设备");
		}
		
		return device;

		
	}

	private static void waitDevicesList(AndroidDebugBridge bridge) {
		
		int count = 0;
		while (bridge.hasInitialDeviceList() == false) {
			try {
				Thread.sleep(500);
				count++;
			} catch (InterruptedException e) {
			}
			if (count > 60) {
				System.err.print("等待获取设备超时");
				break;
			}
		}
	}

}
