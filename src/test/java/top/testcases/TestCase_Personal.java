package top.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import top.base.appium.TestNGListener;
import top.play.pages.BaseCase;
import top.play.pages.PersonPage;

@Listeners(TestNGListener.class)
public class TestCase_Personal extends BaseCase{

	@Test(description = "退出登录")
	public void testLogout() {
		System.out.println("testLogout");
		PersonPage personPage = new PersonPage(mdriver);
		PageFactory.initElements(new AppiumFieldDecorator(mdriver, 20 ,TimeUnit.SECONDS), personPage);
		personPage.logout();
	}

}
