package com.cmall.meiyin.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;

import com.cmall.appium.DriverHelper;
import com.cmall.utils.LogUtil;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TestPage {

	private AndroidDriver<MobileElement> mdriver;
	private DriverHelper helper;
	LogUtil log = new LogUtil(TestPage.class);

	public TestPage() {
	}

	public TestPage(AndroidDriver<MobileElement> driver,DriverHelper helper) {
		this.mdriver = driver;
		this.helper = helper;
	}

	/**
	 * 定制 按钮
	 */
	@AndroidFindBy(id = "com.meitu.wheecam:id/btn_meiyin")
	private MobileElement m_entrance;

	/**
	 * 关闭广告按钮
	 */
	@AndroidFindBy(id = "com.meitu.wheecam:id/meiyin_floating_ad_close_ll")
	private MobileElement m_ad_close;

	@FindBy(id = "com.meitu.wheecam:id/meiyin_webview_wv")
	private MobileElement m_home_page;
	
	/**
	 * 3d3d 坑位
	 */
	@FindBy(xpath="/html/body/div[3]/div[3]/div[2]")
	private MobileElement m_3d_3d;

	@FindBy(xpath = "//*[@class=\"goods-list-new-goods-item-right\"]")
	private List<MobileElement> m_List;

	@FindBy(id = "com.meitu.wheecam:id/meiyin_album_checking_face_brush_iv")
	private MobileElement hint;
	
	/**
	 * 开始定制页面webview
	 */
	@FindBy(id = "com.meitu.wheecam:id/meiyin_webview_wv" )
	private MobileElement m_startBuilt;
	
	/**
	 * 完成定制
	 */
	@FindBy(id= "com.meitu.wheecam:id/tv_title_right_btn")
	private MobileElement m_complete_built;

	/**
	 * 3d模型
	 */
	@FindBy(id = "com.meitu.wheecam:id/rela_webview_parent")
	private MobileElement m_3dModel;
	
	/**
	 * 完成编辑
	 */
	@FindBy(id = "com.meitu.wheecam:id/tv_title_right_btn")
	private MobileElement m_complte_edit;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"照片\")")
	private MobileElement m_pic_entrance;
	
	@FindBy(id = "com.meitu.wheecam:id/meiyin_album_thumb")
	private List<MobileElement> m_pics_list;
	
	/**
	 * 进入3DModel页面
	 */
	public void goto3DModel() {

		try {
			
			if (helper.waitActivity(ActivityList_MeiYin.MainActivity)) {
				helper.clickonElement(m_entrance);
			}
			
			if (helper.waitActivity(ActivityList_MeiYin.MeiYinHomeActivity)) {
				try {
					if (m_ad_close.isDisplayed()) {
						helper.clickonElement(m_ad_close);
					}
				} catch (NoSuchElementException e) {
					log.info("此时页面中不含有广告~");
				}
			}

			if (!helper.waitActivity(ActivityList_MeiYin.MeiYinHomeActivity)) {
				helper.backToActivity(ActivityList_MeiYin.MeiYinHomeActivity);
			}
			
			// 点击 3D3D
			helper.pause(1000);
			int width = helper.getDeviceWidth();
			int height = helper.getDeviceHeight();
			mdriver.tap(1, width / 2, height / 10 * 8, 500);
			if (!helper.waitActivity(ActivityList_MeiYin.MeiYinGoodsDetailActivity)) {
				mdriver.tap(1, width / 2, height / 10 * 8, 500);
				helper.pause_default_time();
				assertEquals(mdriver.currentActivity(), ActivityList_MeiYin.MeiYinGoodsDetailActivity);
			}
			
			// 点击：开始定制
			Thread.sleep(1000);
			helper.tap_StartBuilt();
			if (!helper.waitActivity(ActivityList_MeiYin.MeiYinWebViewActivity)) {
				helper.tap_StartBuilt();
				helper.pause_default_time();
				assertEquals(mdriver.currentActivity(), ActivityList_MeiYin.MeiYinWebViewActivity);
			}
			
			// 点击：白色
			Thread.sleep(1000);
			helper.tap_White();

			// 点击：S码
			Thread.sleep(1000);
			helper.tap_S();

			// 再次点击：开始定制
			Thread.sleep(1000);
			helper.tap_StartBuilt();
			if (!helper.waitActivity(ActivityList_MeiYin.DetailActivity)) {
				helper.tap_StartBuilt();
				helper.pause_default_time();
				assertEquals(mdriver.currentActivity(), ActivityList_MeiYin.DetailActivity);
			}
			
			// 通过判断m_complete_built 是否可以点击，来判断3d模型是否加载完毕
			String result = "true";
			System.out.println("clickable:"+m_complete_built.getAttribute("clickable"));

			int i = 0;
			while (!result.equals(m_complete_built.getAttribute("clickable"))) {
				Thread.sleep(1000);
				i++;
				if (result.equals(m_complete_built.getAttribute("clickable"))) {
					break;
				}
				if (i > 20) {
					assertTrue(false,"3d模型加载失败");
				}
			}
			
			System.out.println("clickable:"+m_complete_built.getAttribute("clickable"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
