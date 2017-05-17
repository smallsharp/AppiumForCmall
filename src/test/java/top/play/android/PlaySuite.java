package top.play.android;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import top.base.utils.MyBaseCase;
import top.base.utils.TestNGListener;


@Listeners({TestNGListener.class})
public class PlaySuite extends MyBaseCase{
		
	@Test(description="登录")
	@Parameters({"mobile","password"})//参数名称需要和xml中对应
	public void testLogin(String mobile,String password) throws InterruptedException {
		LoginPage login = PageFactory.initElements(mdriver, LoginPage.class);
		login.login(mobile, password);
	}
	
	@Test(description="购买男装")
	public void testBuyMenWear() throws InterruptedException{
		Play_DIYPage diyPage = PageFactory.initElements(mdriver, Play_DIYPage.class);
		diyPage.buyMenWear();
	}
	
	@Test(description="个人设置")
	public void testModifyPersonalInfo() {
		PersonModifyPage personModifyPage = PageFactory.initElements(mdriver, PersonModifyPage.class);
		personModifyPage.modifyPersonInfo();
	}
	
	@Test(description="添加收货地址")
	public void testAddAdress(){
		PersonPage personPage = PageFactory.initElements(mdriver, PersonPage.class);
		personPage.add_Delivery_Address();

	}
	
	@Test(description="退出登录")
	public void testLogout(){
		PersonPage personPage = PageFactory.initElements(mdriver, PersonPage.class);
		personPage.logout();
	}
	

}
