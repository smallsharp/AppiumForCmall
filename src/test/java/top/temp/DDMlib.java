package top.temp;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;
import com.android.ddmlib.TimeoutException;

/**
 * 需要ddmlib.jar
 * 
 * @author Administrator
 *
 */

public class DDMlib {

	public static IDevice device;

	public static void main(String[] args) {

		AndroidDebugBridge.init(false); // 很重要
		device = getDevice();
		System.out.println(device.getSerialNumber());
		System.out.println(device.isOnline());
		System.out.println(device.isOffline());

		AndroidDebugBridge.init(false); //
		DDMlib screenshot = new DDMlib();

		for (int i = 0; i < 10; i++) {
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-HH-mm-ss");
			String nowTime = df.format(date);
			screenshot.getScreenShot(device, "Robotium" + nowTime);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static IDevice getDevice() {
		IDevice device = null;
		AndroidDebugBridge bridge = AndroidDebugBridge.createBridge();
		waitDevicesList(bridge);
		try {

			IDevice devices[] = bridge.getDevices();
			if (devices.length >= 0) {
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

	private BufferedImage image = null;

	public void getScreenShot(IDevice device, String filename) {
		RawImage rawScreen = null;
		try {
			rawScreen = device.getScreenshot();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AdbCommandRejectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rawScreen != null) {
			Boolean landscape = false;
			int width2 = landscape ? rawScreen.height : rawScreen.width;
			int height2 = landscape ? rawScreen.width : rawScreen.height;
			if (image == null) {
				image = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_RGB);
			} else {
				if (image.getHeight() != height2 || image.getWidth() != width2) {
					image = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_RGB);
				}
			}
			int index = 0;
			int indexInc = rawScreen.bpp >> 3;
			for (int y = 0; y < rawScreen.height; y++) {
				for (int x = 0; x < rawScreen.width; x++, index += indexInc) {
					int value = rawScreen.getARGB(index);
					if (landscape)
						image.setRGB(y, rawScreen.width - x - 1, value);
					else
						image.setRGB(x, y, value);
				}
			}
			try {
				ImageIO.write((RenderedImage) image, "PNG", new File("D:/" + filename + ".jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
