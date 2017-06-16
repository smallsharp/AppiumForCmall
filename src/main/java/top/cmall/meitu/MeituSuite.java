package top.cmall.meitu;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import top.base.appium.DriverFactory;
import top.base.appium.Helper;
import top.base.appium.TestNGListener;

@Listeners(TestNGListener.class)
public class MeituSuite {

	private AndroidDriver<MobileElement> mdriver = null;

	@BeforeClass
	public void setup() {
		Reporter.log("========== 正在准备测试环境，预计20s，请稍后 ==========", true);
		mdriver= DriverFactory.getInstance().initMeituDriver();
		Helper.setDriver(mdriver);
	}

	@AfterClass
	public void teardown() throws InterruptedException {

		if (mdriver != null) {
			mdriver.quit();
		}
		Reporter.log("========== 测试用例执行完成，BYE BYE ==========", true);
	}

	
	@Test
	public void test(){
		MeituPage meituPage = new MeituPage(mdriver);
		
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 10 ,TimeUnit.SECONDS), meituPage);
		meituPage.goto3DModel();
	}

}
