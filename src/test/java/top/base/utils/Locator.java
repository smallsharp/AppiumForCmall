package top.base.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class Locator {

	private static final Logger log = Logger.getLogger(Locator.class);
	private static MyAndroidDriver<WebElement> mdriver = Driver.newInstance();

	/**
	 * 
	 * @Description 动态等待activity出现
	 * @Data 2017年5月3日
	 * @return true or false
	 */
	public static boolean waitForActivity(String activityName) {
		boolean flag = false;
		int i = 0;
		while (i < 5) {
			try {
				if (activityName.contains(mdriver.currentActivity())) {
					log.info(activityName + " Found!");
					flag = true;
					break;
				} else {
					log.info(activityName + ",第" + i + "次等待，Not Found!");
					i++;
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				i++;
				log.error(activityName + ",第" + i + "次等待，Not Found!");
			}
		}
		return flag;
	}

	/**
	 * 
	 * @Description 通过元素id等，查询多次，定位元素
	 * @Data 2017年5月3日
	 * @return WebElement
	 */
	
	static WebElement element = null;

	public static WebElement findElementById(String resourceId) throws InterruptedException {
		int i = 0;
		while (i < 3) {
			try {
				// 如果找不到，会抛出异常
				if ((element = mdriver.findElementById(resourceId)).isDisplayed()) {
					log.info(resourceId + ",控件 found！");
					break;
				}
			} catch (Exception e) {
				log.error(resourceId + ",第" + i + "次等待，Not Found!");
				i++;
			}
		}
		return element;
	}

}