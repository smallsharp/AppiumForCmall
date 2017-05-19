package top.temp;

import java.awt.image.BufferedImage;
import java.io.File;
import top.base.utils.ImageUtil;

public class Temp {

	public static void main(String[] args) {
		
		
		String expected_path = System.getProperty("user.dir")+"\\test-output\\pics_expected";
		String actul_path = System.getProperty("user.dir")+"\\test-output\\pics_actul";

		File imgA = new File(actul_path,"e_login.jpg");
		File imgB = new File(actul_path,"e_login2.jpg");
		
		BufferedImage a = ImageUtil.getImageFromFile(imgA);
		BufferedImage b = ImageUtil.getImageFromFile(imgB);
		
		boolean same = ImageUtil.sameAs(a, b, 1);
		
		boolean same2 = ImageUtil.sameAs(imgA, imgB, 0.8);
		System.out.println(same+"---"+same2);
		ImageUtil.getSamePercentFrom(a, b);
//		ImageUtil.getSamePercentFrom(imgA, imgB);
		
	}

}
