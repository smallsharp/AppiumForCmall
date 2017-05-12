package top.cases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import top.base.listener.TestNGListener;
import top.base.utils.CommandUtils;
import top.base.utils.Driver;
import top.base.utils.MyBaseCase;
import top.pages.LoginPage;
import top.pages.PersonPage;


@Listeners({TestNGListener.class})
public class LoginCase extends MyBaseCase{
		
	@Test
	@Parameters({"mobile","password"})//参数名称需要和xml中对应
	public void testLogin(String mobile,String password) throws InterruptedException {
		LoginPage login = PageFactory.initElements(mdriver, LoginPage.class);
		login.login(mobile, password);
	}
	
	@Test
	public void testLogout(){
		PersonPage personPage = PageFactory.initElements(mdriver, PersonPage.class);
		personPage.logout();
	}
	

}
