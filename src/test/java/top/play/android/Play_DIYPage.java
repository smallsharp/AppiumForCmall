package top.play.android;

import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.android.AndroidDriver;
import top.base.utils.Assist;
import top.base.utils.Driver;

public class Play_DIYPage {

	AndroidDriver<WebElement> mdriver = Driver.newInstance();

	@FindBy(id = "com.tude.android:id/btn_jump")
	private WebElement e_skipVideo; // 跳过视频

	@FindBy(id = "com.tude.android:id/btn_diy")
	private WebElement m_btn_diy;// 定制

	@FindBy(id = "com.tude.android:id/sdv_image")
	private List<WebElement> m_productlist;// 定制页面-产品list

	@FindBy(id = "com.tude.android:id/img_back")
	private WebElement m_back;// 一级返回按钮

	@FindBy(id = "com.tude.android:id/tv_title")
	private WebElement m_title;// 男装

	@FindBy(xpath = "//*[@id='pageWrap']/section/div/ul/li[1]/a/img")
	private WebElement m_tee; // 男装-TEE

	@FindBy(id = "com.tude.android:id/btn_buy")
	private WebElement m_btn_buy; // 去购买

	public void buyMenWear() throws InterruptedException {

		if (!Assist.isActivityDisplayed(Constant.HOME_ACTIVITY)) {
			assertTrue(false, "HOME_ACTIVITY is not found");
		}

		m_productlist.get(1).click();// 点击：定制-男装

		if (!Assist.isActivityDisplayed(Constant.PRODUCT_CLASSIFITION_ACTIVITY)) {
			assertTrue(false, "PRODUCT_CLASSIFITION_ACTIVITY is not found");
		}

		Assist.switchToWebView();// 切换到webview
		m_tee.click();
		Assist.switchToNative();// 切换到native
		
		if (m_btn_buy.isDisplayed()) {
			m_btn_buy.click();
			assertTrue(true);
		}
	}

}
