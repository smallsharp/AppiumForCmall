package top.temp;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import top.base.utils.CommandUtil;

public class Temp {

	public static void main(String[] args) {
		
		
/*		File imageA = new File(Constant.actul_path,"man_tee_act3.png");
		File imageB = new File(Constant.actul_path,"man_tee_act2.png");
		
		ImageUtil.getSamePercentFrom(imageA, imageB);*/
		
		try {
			
			String shoot = "adb shell screencap -p /sdcard/screen2.png";
			Thread.sleep(2000);
			String pull = "adb pull /sdcard/screen1.png d:\12345.png";
/*			CommandUtils.exec_cmd(shoot);
			CommandUtils.exec_cmd(pull);*/
						
			System.out.println("111"+getShellOut(Runtime.getRuntime().exec(shoot)));
//			System.out.println("222"+getShellOut(Runtime.getRuntime().exec(pull)));
			Runtime.getRuntime().exec(pull);


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getShellOut(Process ps) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = shellOut(ps);
		String line;

		try {
			while ((line = br.readLine()) != null) {
				// sb.append(line);
				sb.append(line + System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString().trim();
	}
	
	public static BufferedReader shellOut(Process ps) {
		BufferedInputStream in = new BufferedInputStream(ps.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		return br;
	}



}
