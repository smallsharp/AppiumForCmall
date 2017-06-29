package top.testcases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import top.base.appium.TestNGListener;
import top.play.pages.BaseCase;
import top.play.pages.LoginPage;
import top.play.pages.ModelPage;
import top.play.pages.PersonModifyPage;
import top.play.pages.PersonPage;

@Listeners(TestNGListener.class)
public class TestCase_Model extends BaseCase{

	@BeforeSuite
	public void BeforeSuite() {
		System.out.println("Before Suite");
		if (mdriver == null) {
			mdriver = BaseCase.setup();
		}
	}
	
	@AfterSuite
	public void AfterSuite() {
		System.out.println("After Suite");
	}
	
	@BeforeClass
	public void setup2() {
		System.out.println("Before Class");

	}
	
	@AfterClass
	public void teardown2() {
		System.out.println("After Class");
	}

	@Test(description = "通过账号密码登录")
	@Parameters({ "mobile", "password" }) // 参数名称需要和xml中对应
	public void testLogin(String mobile, String password) {
		LoginPage loginPage = new LoginPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS), loginPage);
		loginPage.login_help(mobile, password);
	}

	@Test(description = "3d模型列表展示")
	public void testCheck3DModelGoodsList() {
		ModelPage modelPage = new ModelPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS),modelPage);
		modelPage.check3DModelGoodsList();
	}
	
	@Test(description = "3d模型展示-不同的颜色和等级")
	public void testCheck3DModelByColorAndGrade() {
		ModelPage modelPage = new ModelPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS),modelPage);
		modelPage.check3DModelByColorAndGrade();
	}

	@Test(description = "个人设置")
	public void testModifyPersonalInfo() {
		PersonModifyPage personModifyPage = PageFactory.initElements(mdriver, PersonModifyPage.class);
		personModifyPage.modifyPersonInfo();
	}

	@Test(description = "添加收货地址")
	public void testAddAdress() {
		PersonPage personPage = PageFactory.initElements(mdriver, PersonPage.class);
		personPage.add_Delivery_Address();

	}

	@Test(description = "退出登录")
	public void testLogout() {
		PersonPage personPage = PageFactory.initElements(mdriver, PersonPage.class);
		personPage.logout();
	}

}
