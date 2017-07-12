package com.cmall.meiyin.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.cmall.appium.DriverFactory;
import com.cmall.appium.Helper;
import com.cmall.appium.TestNGListener;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@Listeners(TestNGListener.class)
public class MeituSuite {

	private AndroidDriver<MobileElement> mdriver = null;
	private Helper helper;
	@BeforeClass
	public void setup() {
		Reporter.log("========== 正在准备测试环境，请稍后 ==========", true);
		mdriver= DriverFactory.initDriver(4723, "022TAS7N51009853");
	}

	@AfterClass
	public void teardown() {

		if (mdriver != null) {
			mdriver.quit();
		}
		Reporter.log("========== 测试用例执行完成，BYE BYE ==========", true);
	}

	@Test
	public void test(){
		TestPage testPage = new TestPage(mdriver, helper);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 10 ,TimeUnit.SECONDS), testPage);
		testPage.goto3DModel();
		
	}
	
//	@Test(priority=10)
	public void test_goto3DModel(){
		
		MeituPage meituPage = new MeituPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 10 ,TimeUnit.SECONDS), meituPage);
		meituPage.goto3DModel();
		
//		meituPage.goto3DModel_by_xpath();
	}
	
//	@Test(priority=20)
	public void test_checkModel_WithoutEdit(){
		
		MeituPage meituPage = new MeituPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 10 ,TimeUnit.SECONDS), meituPage);
		meituPage.checkModel_WithoutEdit();
		
	}
	
//	@Test(priority=30)
	public void test_checkModel_WithEdit(){
		
		MeituPage meituPage = new MeituPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 10 ,TimeUnit.SECONDS), meituPage);
		meituPage.checkModel_WithEdit();
		
	}

}
