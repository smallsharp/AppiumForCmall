package top.cmall.meitu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import top.base.appium.Helper;
import top.base.utils.LogUtil;

public class MeituPage {

	private AndroidDriver<MobileElement> mdriver;

	private static LogUtil log = new LogUtil(MeituPage.class);

	public MeituPage() {
	}

	public MeituPage(AndroidDriver<MobileElement> driver) {
		this.mdriver = driver;
	}

	/**
	 * 定制 按钮
	 */
	@AndroidFindBy(id = "com.meitu.wheecam:id/btn_dailog")
	private MobileElement m_entrance;

	@AndroidFindBy(id = "com.meitu.wheecam:id/meiyin_floating_ad_close_ll")
	private MobileElement m_ad_close;

	@FindBy(id = "com.meitu.wheecam:id/meiyin_webview_wv")
	private MobileElement m_home_page;

	@FindBy(xpath = "//*[@class=\"goods-list-new-goods-item-right\"]")
	private List<MobileElement> m_List;

	@FindBy(id = "com.meitu.wheecam:id/meiyin_album_checking_face_brush_iv")
	private MobileElement hint;
	
	@FindBy(id= "com.meitu.wheecam:id/tv_title_right_btn")
	private MobileElement m_complete;

	public void goto3DModel() {

		try {

			Helper.clickonElement(m_entrance);
			
			try {
				if (m_ad_close.isDisplayed()) {
					Helper.clickonElement(m_ad_close);
				}
			} catch (NoSuchElementException e) {
				System.out.println("此时页面中不含有广告~");
			}
			
			if (!Helper.waitElement("com.meitu.meiyin.app.web.MeiYinHomeActivity", m_home_page)) {
				assertTrue(false);
			}

			if (!Helper.swipe_up_until_find("3D3D")) {
				assertTrue(false, "没有找到3D");
			}

			int width = mdriver.manage().window().getSize().width;
			int height = mdriver.manage().window().getSize().height;
			// 点击 3D3D
			mdriver.tap(1, width / 2, height / 10 * 8, 500);

			if (!Helper.waitActivity("com.meitu.meiyin.app.web.MeiYinGoodsDetailActivity")) {
				assertEquals(mdriver.currentActivity(), "com.meitu.meiyin.app.web.MeiYinGoodsDetailActivity");
			}

			// WebElement element = mdriver.findElementByAndroidUIAutomator("new
			// UiSelector().text(\"开始定制\")");

			/*
			 * Helper.switchToWebView(); WebElement element =
			 * mdriver.findElementByXPath("//*[@data-type=\"custom\"]");
			 * System.out.println("A:"+element.isDisplayed());
			 * System.out.println("B:"+element.isEnabled());
			 * System.out.println("C:"+element.getText());
			 * System.out.println("D:"+element.getSize());
			 * System.out.println("E:"+element.getLocation()); element.click();
			 */

			Thread.sleep(3000);
			// 点击：开始定制
			mdriver.tap(1, width / 2, height / 100 * 98, 500);
			Thread.sleep(3000);
			
			if (!Helper.waitActivity("com.meitu.meiyin.app.web.MeiYinWebViewActivity")) {
				assertEquals(mdriver.currentActivity(), "com.meitu.meiyin.app.web.MeiYinWebViewActivity");
			}
			
			// 点击：白色
			mdriver.tap(1, width / 100 * 13, height / 100 * 67, 500);
			Thread.sleep(1000);

			// 点击：S
			mdriver.tap(1, width / 100 * 13, height / 100 * 77, 500);
			Thread.sleep(1000);

			// 点击：开始定制
			mdriver.tap(1, width / 2, height / 100 * 98, 500);

			if (!Helper.waitActivity("com.tude.android.demo_3d.sample.activities.DetailActivity")) {
				assertEquals(mdriver.currentActivity(), "com.tude.android.demo_3d.sample.activities.DetailActivity");
			}
			
			System.out.println("clickable:"+m_complete.getAttribute("clickable"));

			String result = "true";
			
			try {
				int i = 0;
				while (!result.equals(m_complete.getAttribute("clickable"))) {
					Thread.sleep(1000);
					if (result.equals(m_complete.getAttribute("clickable"))) {
						break;
					}
					if (i > 20) {
						assertTrue(false,"3d模型加载失败");
					}
				}
			} catch (Exception e) {
				
			}
			
			System.out.println("clickable:"+m_complete.getAttribute("clickable"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
