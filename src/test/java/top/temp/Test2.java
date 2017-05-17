package top.temp;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import top.base.utils.CommandUtils;

public class Test2 {

	public static void main(String[] args) {
		
		AppiumDriverLocalService.buildDefaultService();


//		CommandUtils.exec_shell("pm clear com.tude.android");
		
		CommandUtils.exec_shell("am start -n " + " com.tude.android/.activity.SplashActivity");
	}

}
