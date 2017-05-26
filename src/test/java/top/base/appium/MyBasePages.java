package top.base.appium;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MyBasePages {

	protected AndroidDriver<MobileElement> mdriver;
	
	public MyBasePages() {
		init();
	}
	
	public void init() {
		if(mdriver==null){
			DriverFactory dFactory = DriverFactory.getInstance();
			mdriver = dFactory.initAndroidDriver();
			System.out.println("MyBasePages driver:"+ mdriver.toString());
		}
	}

}
