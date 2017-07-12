package com.cmall.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 利用ResourceBundle读取资源配置文件
 * @author cm
 *
 */
public class PropertyUtil {

	static Properties prop = new Properties();
	static ResourceBundle rb = ResourceBundle.getBundle("apple");
	
	public PropertyUtil(String filePath) {

		InputStream in = null;
		try {
			// in = new BufferedInputStream(new
			// FileInputStream(filePath));//读取工程目录下的文件
			in = this.getClass().getResourceAsStream(filePath);
			prop.load(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}


	/**
	 * 通过Properties 获取value
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return prop.getProperty(key);
	}
	
	/**
	 * 通过ResourceBundle 获取value
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		return rb.getString(key);
	}
	

	public static void main(String[] args) {
//		PropertyUtil propertyTest = new PropertyUtil("/app.properties");// 这里需要加个/，表示类的根目录
//		String s1 = propertyTest.getValue("deviceName_meizu");
//		System.out.println(s1);

		ResourceBundle rb = ResourceBundle.getBundle("apple");// 不需要加 文件后缀名
		String s2 = rb.getString("password");
		System.out.println(s2);
	}
}
