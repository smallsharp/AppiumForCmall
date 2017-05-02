package top.temp;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;

public class Android {

	public static IDevice device;

	public static void main(String[] args) {

		AndroidDebugBridge.init(false); // 很重要
		device = getDevice();
		System.out.println(device.getFileListingService().getRoot());
		System.out.println(device.getAvdName());
	}

	private static IDevice getDevice() {
		IDevice device = null;
		AndroidDebugBridge bridge = AndroidDebugBridge.createBridge();
		waitDevicesList(bridge);
		IDevice devices[] = bridge.getDevices();
		if (devices.length < 0) {
			System.err.print("没有检测到 个设备");
		}
		else {
			device = devices[0];
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
