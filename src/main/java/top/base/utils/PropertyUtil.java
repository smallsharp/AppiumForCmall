package top.base.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

	Properties prop = new Properties();

	/**
	 * @author tester_lee
	 * @param file
	 */
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
	 * 
	 * @param key
	 * @return value
	 */
	public String getValue(String key) {

		String value = prop.getProperty(key);

		return value;

	}

	public static void main(String[] args) {

		PropertyUtil propertyTest = new PropertyUtil("/app.properties");// 这里需要加个/，表示类的根目录

		String s1 = propertyTest.getValue("deviceName_meizu");
		System.out.println(s1);

	}
}
