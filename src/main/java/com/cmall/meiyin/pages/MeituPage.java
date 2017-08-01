package com.cmall.meiyin.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import com.cmall.appium.DriverHelper;
import com.cmall.play.pages.Constant;
import com.cmall.utils.ImageUtil;
import com.cmall.utils.LogUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MeituPage {

	private AndroidDriver<MobileElement> mdriver;
	private DriverHelper helper;
	private static LogUtil log = new LogUtil(MeituPage.class);

	public MeituPage() {
	}

	public MeituPage(AndroidDriver<MobileElement> driver) {
		this.mdriver = driver;
		helper = new DriverHelper(mdriver);
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
	
	public void goto3DModel_by_xpath() {

		try {
			
			if (!helper.waitElement(ActivityList_MeiYin.MeiYinHomeActivity, m_home_page)) {
				assertTrue(false);
			}
			
			helper.context_to_native();
			log.info("\n"+"准备切换到webview");
			mdriver.context("WEBVIEW_com.meitu.wheecam");
//			Helper.context_to_webview();
			log.info("\n"+"准备scroll");	
//			Helper.scrollToElement(m_3d_3d);// 滑动到指定高度
			helper.scrollToElement(m_3d_3d);
			helper.context_to_native();
			
			// 点击 3D3D
			int width = mdriver.manage().window().getSize().width;
			int height = mdriver.manage().window().getSize().height;
			mdriver.tap(1, width / 2, height / 5 * 1, 500);
			
			log.info("x:"+width+",y:"+height);
			Thread.sleep(500);
			if (!helper.waitActivity(ActivityList_MeiYin.MeiYinGoodsDetailActivity)) {
				assertEquals(mdriver.currentActivity(), ActivityList_MeiYin.MeiYinGoodsDetailActivity);
			}
			
//			WebElement element = mdriver.findElement(By.xpath("//*[@data-type=\"custom\"]"));
//			WebElement element = mdriver.findElementByXPath("//div[@data-goods-type=3]");
			
			log.info("\n"+"准备点击开始定制3");
			mdriver.tap(1, width / 2, height / 1280 * 1250, 500);

			assertEquals(1, 2);

			if (!helper.waitActivity(ActivityList_MeiYin.MeiYinWebViewActivity)) {
				assertEquals(mdriver.currentActivity(), ActivityList_MeiYin.MeiYinWebViewActivity);
			}
			Thread.sleep(1000);

			// 点击：白色
			mdriver.tap(1, width / 100 * 13, height / 80 * 53, 500);
			Thread.sleep(1000);

			// 点击：S码
			mdriver.tap(1, width / 100 * 13, height / 30 * 23, 500);

			Thread.sleep(1000);

			// 再次点击：开始定制
			mdriver.tap(1, width / 2, height / 100 * 98, 500);

			if (!helper.waitActivity(ActivityList_MeiYin.DetailActivity)) {
				assertEquals(mdriver.currentActivity(), ActivityList_MeiYin.DetailActivity);
			}
			
			// 通过判断m_complete_built 是否可以点击，来判断3d模型是否加载完毕
			String result = "true";
			System.out.println("clickable:"+m_complete_built.getAttribute("clickable"));

			int i = 0;
			while (!result.equals(m_complete_built.getAttribute("clickable"))) {
				Thread.sleep(1000);
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
	
	/**
	 * 进入模型修改页面，不做任何操作，保存后，前后对比。
	 * 预期：一样
	 */
	public void checkModel_WithoutEdit(){
		
		if (!helper.waitActivity(ActivityList_MeiYin.DetailActivity)) {
			helper.backToActivity(ActivityList_MeiYin.MeiYinHomeActivity);
			goto3DModel();
		}
		
		String model_first_jpg = "model_first_withoutEdit_"+ helper.getDeviceName()+".jpg";
		helper.takeScreenShot(model_first_jpg);
		BufferedImage image_model_first = ImageUtil.getImageFromFile(new File(Constant.ACTUL_PATH, model_first_jpg));
		if (!helper.waitElement("com.tude.android.demo_3d.sample.activities.DetailActivity",m_3dModel)) {
			assertEquals(mdriver.currentActivity(), "com.tude.android.demo_3d.sample.activities.DetailActivity");
		}
		
		helper.clickonElement(m_3dModel);
		
		if (!helper.waitElement(ActivityList_MeiYin.UCropEditActivity,m_complte_edit)) {
			assertEquals(mdriver.currentActivity(), ActivityList_MeiYin.UCropEditActivity);
		}
		
		helper.clickonElement(m_complte_edit);
		
		if (!helper.waitElement(ActivityList_MeiYin.DetailActivity,m_3dModel)) {
			assertEquals(mdriver.currentActivity(), ActivityList_MeiYin.DetailActivity);
		}
		String model_second_jpg = "model_second_withoutEdit_"+ helper.getDeviceName()+".jpg";
		helper.takeScreenShot(model_second_jpg);
		BufferedImage image_model_second = ImageUtil.getImageFromFile(new File(Constant.ACTUL_PATH,model_second_jpg));
		
		assertTrue(ImageUtil.sameAs(image_model_first, image_model_second, 0.9));
	}
	
	/**
	 * 编辑3d模型
	 */
	public void checkModel_WithEdit(){
		
		if (!helper.waitActivity(ActivityList_MeiYin.DetailActivity)) {
			helper.backToActivity(ActivityList_MeiYin.MeiYinHomeActivity);
			goto3DModel();
		}
		
		String model_first_jpg = "model_first_withoutEdit_"+ helper.getDeviceName() +".jpg";
		helper.takeScreenShot(model_first_jpg);
		BufferedImage image_model_first = ImageUtil.getImageFromFile(new File(Constant.ACTUL_PATH, model_first_jpg));
		
		if (!helper.waitElement("com.tude.android.demo_3d.sample.activities.DetailActivity",m_3dModel)) {
			assertEquals(mdriver.currentActivity(), "com.tude.android.demo_3d.sample.activities.DetailActivity");
		}
		
		helper.clickonElement(m_3dModel);
		
		if (!helper.waitElement("com.tude.android.demo_3d.sample.activities.ucrop.UCropEditActivity",m_pic_entrance)) {
			assertEquals(mdriver.currentActivity(), "com.tude.android.demo_3d.sample.activities.ucrop.UCropEditActivity");
		}
		
		helper.clickonElement(m_pic_entrance);
		
		if (!helper.waitActivity(ActivityList_MeiYin.MeiYinAlbumActivity)) {
			
		}
		
		helper.clickonElement(m_pics_list.get(0));
		
		if (!helper.waitElement(ActivityList_MeiYin.UCropEditActivity, m_complte_edit)) {
			
		}
		
		helper.clickonElement(m_complte_edit);
		
		if (!helper.waitElement("com.tude.android.demo_3d.sample.activities.DetailActivity",m_3dModel)) {
			assertEquals(mdriver.currentActivity(), "com.tude.android.demo_3d.sample.activities.DetailActivity");
		}
		String model_second_jpg = "model_second_withEdit_"+helper.getDeviceName()+".jpg";
		helper.takeScreenShot(model_second_jpg);
		BufferedImage image_model_second = ImageUtil.getImageFromFile(new File(Constant.ACTUL_PATH,model_second_jpg));
		
		ImageUtil.sameAs(image_model_first, image_model_second, 0.9);
	}

}
