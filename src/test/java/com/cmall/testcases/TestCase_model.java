package com.cmall.testcases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import com.cmall.appium.TestNGListener;
import com.cmall.play.pages.LoginPage;
import com.cmall.play.pages.ModelPage;
import com.cmall.utils.LogUtil;
import com.cmall.utils.PropertyUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@Listeners(TestNGListener.class)
public class TestCase_model implements ITestCase{
	private AndroidDriver<MobileElement> mdriver;
	LogUtil log = new LogUtil(TestCase_model.class);
	@Override
	public void runCase() {
		LoginPage loginPage = new LoginPage(mdriver);
		ModelPage modelPage = new ModelPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS), loginPage);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20,TimeUnit.SECONDS),modelPage);
		loginPage.login(PropertyUtil.getString("mobile"), PropertyUtil.getString("password"));
		modelPage.check3DModelGoodsList();
	}

	@Override
	public void setDriver(AndroidDriver<MobileElement> mdriver) {
		this.mdriver = mdriver;		
	}

}
