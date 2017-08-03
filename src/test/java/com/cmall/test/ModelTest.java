package com.cmall.test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cmall.appium.TestNGListener;
import com.cmall.play.pages.LoginPage;
import com.cmall.play.pages.ModelPage;
import com.cmall.play.pages.PersonModifyPage;
import com.cmall.play.pages.PersonPage;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * 使用继承
 * @author cm
 *
 */
@Listeners(TestNGListener.class)
public class ModelTest extends BaseCase{

	@Test(description = "通过账号密码登录")
	public void testLogin() {
		LoginPage loginPage = new LoginPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS), loginPage);
		loginPage.login("18521035133", "111111");
	}

//	@Test(description = "3d模型列表展示")
	public void testCheck3DModelGoodsList() {
		ModelPage modelPage = new ModelPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS),modelPage);
		modelPage.check3DModelGoodsList();
	}
	
//	@Test(description = "3d模型展示-不同的颜色和等级")
	public void testCheck3DModelByColorAndGrade() {
		ModelPage modelPage = new ModelPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS),modelPage);
		modelPage.check3DModelByColor();
	}

//	@Test(description = "个人设置")
	public void testModifyPersonalInfo() {
		PersonModifyPage personModifyPage = PageFactory.initElements(mdriver, PersonModifyPage.class);
		personModifyPage.modifyPersonInfo();
	}

//	@Test(description = "添加收货地址")
	public void testAddAdress() {
		PersonPage personPage = PageFactory.initElements(mdriver, PersonPage.class);
		personPage.add_Delivery_Address();

	}

}
