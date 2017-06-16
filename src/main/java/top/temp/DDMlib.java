package top.temp;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
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
	
	IDevice device;
	
	public static void main(String[] args) {
		DDMlib ddMlib = new DDMlib();
		ddMlib.test();
	}
	
	
	public void test(){
		
		device = this.getDevice();
		System.out.println(device.getSerialNumber());
		System.out.println(device.isOnline());
		System.out.println(device.getState());

	}

	private IDevice getDevice() {
		
		AndroidDebugBridge.init(false);
		AndroidDebugBridge bridge = AndroidDebugBridge.createBridge();
		try {
			Thread.sleep(1000);
			//Calling getDevices() right after createBridge(String, boolean) will generally result in an empty list.
			if (bridge.hasInitialDeviceList()) {
				IDevice devices[] = bridge.getDevices();
				if (devices.length >= 0) {
					device = devices[0];
				}
				return device;
			}
			System.out.println("没有检测到设备");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}

	private BufferedImage image = null;

	public void getScreenShot(IDevice device, String filename) {
		RawImage rawScreen = null;
		try {
			rawScreen = device.getScreenshot();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (AdbCommandRejectedException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
