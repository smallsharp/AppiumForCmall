package top.tude.android;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import top.base.utils.MyBaseCase;
import top.base.utils.TestNGListener;
import top.play.android.LoginPage;
import top.play.android.PersonPage;


@Listeners({TestNGListener.class})
public class TestSuite_Tude extends MyBaseCase{
		
	@Test(description="登录")
	@Parameters({"mobile","password"})//参数名称需要和xml中对应
	public void testLogin(String mobile,String password) throws InterruptedException {
		LoginPage login = PageFactory.initElements(mdriver, LoginPage.class);
		login.login(mobile, password);
	}
	
	@Test(description="退出登录")
	public void testLogout(){
		PersonPage personPage = PageFactory.initElements(mdriver, PersonPage.class);
		personPage.logout();
	}
	
	@Test(description="购买男装示例")
	public void testBuyMenWear(){
		DIYPage diyPage = PageFactory.initElements(mdriver, DIYPage.class);
		diyPage.buyMenWear();
	}
	

}
