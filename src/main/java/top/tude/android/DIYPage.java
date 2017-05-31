package top.tude.android;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.android.AndroidDriver;
import top.base.appium.DriverFactory;
import top.base.appium.Helper;
import top.play.android.ActivityList;

public class DIYPage {
	
	AndroidDriver<WebElement> mdriver;
	
	// 跳过
	@FindBy(id = "com.tude.android:id/btn_jump")
	private WebElement e_skipVideo;
	
	@FindBy(id="com.tude.android:id/btn_diy")
	private WebElement m_btn_diy;// 定制
	
	@FindBy(id="com.tude.android:id/sdv_image")
	private List<WebElement> m_productlist;// 产品list
	
	@FindBy(id="com.tude.android:id/img_back")
	private WebElement m_back;// 一级返回按钮
	
	@FindBy(className="com.tencent.smtt.webkit.WebView")
	private WebElement m_webview;//一级 webview
	
	@FindBy(id="com.tude.android:id/tv_title")
	private WebElement m_title;// 男装
	
	@FindBy(xpath="//*[@id='pageWrap']/section/div/ul/li[1]/a/img")
	private WebElement m_tee; // 男装-TEE
	
	@FindBy(id="com.tude.android:id/btn_buy")
	private WebElement m_btn_buy; // 去购买
	
	@FindBy(id="com.tude.android:id/sdv_item")
	private List<WebElement> m_sdv_item;
	
	@FindBy(xpath="/html/body/div[1]/div[2]/div/ul/li[2]")
	private WebElement m_thirt;// 短袖
	
	public void buyMenWear(){
		if (Helper.isActivityDisplayed(ActivityList.VIDEO_ACTIVITY)) {
			e_skipVideo.click();
		}
		if(Helper.isActivityDisplayed(ActivityList.HOME_ACTIVITY)){
			m_productlist.get(m_productlist.size()-1).click();
			
			if (Helper.isActivityDisplayed(ActivityList.PRODUCT_CLASSIFITION_ACTIVITY)) {
		        Helper.slideUP();
		        Helper.slideDown();
				
				m_tee.click();
				
//				Assist.pageShift();
/*		        WebDriverWait wait = new WebDriverWait(mdriver, 20);
		        ExpectedConditions.visibilityOf(m_webview);*/

		        try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        m_btn_buy.click();
				
			}
			
		}
	}
	
	
}
