package top.base.appium;

import java.util.ArrayList;
import java.util.List;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;


/**
 * 需要ddmlib.jar
 * 
 * @author lee
 *
 */
public class DDMlibUtil {
	
	private IDevice device;
	private static AndroidDebugBridge bridge;

	public static void main(String[] args) {
		List<String> list = getDeviceNames();
		System.out.println(list.get(0));
	}
	
	
	public void test(){
		bridge = AndroidDebugBridge.createBridge();
		if (waitForDevice(bridge)) {
			device = getFirstDevice();
		}
		System.out.println(device.getSerialNumber());
		System.out.println(device.isOnline());
		System.out.println(device.getState());
		device.getProperties();	// 结合 adb shell cat /system/build.prop 的key使用
		System.out.println(device.getProperty("ro.product.brand"));
		System.out.println(device.getProperty("ro.product.cpu.abi"));
		System.out.println(device.getProperty("dalvik.vm.heapsize"));
	}
	
	public static List<String> getDeviceNames(){
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
	
	/**
	 * it should be run firstly
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            count ++;
            if (count > 100) {
                System.err.print("[Time Out] No Devices Found !");
                return false;
            }
        }
		return true;
    }
	
	public static IDevice getFirstDevice(){
		
		if (waitForDevice(bridge)) {
			IDevice devices [] = bridge.getDevices();
			if (devices.length > 0) {
				return devices[0];
			}
		}
		return null;
	}

}
