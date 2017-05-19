package top.play.android;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.android.AndroidDriver;
import top.base.utils.Helper;
import top.base.utils.Driver;

public class Play_DIYPage {

	AndroidDriver<WebElement> mdriver = Driver.newInstance();
	
	/**
	 * 定制 按钮
	 */
	@FindBy(id = "com.tude.android:id/btn_diy")
	private WebElement m_btn_diy;// 定制

	/**
	 * 定制页面-产品list
	 */
	@FindBy(id = "com.tude.android:id/sdv_image")
	private List<WebElement> m_sdv_image; 

	/**
	 * 一级返回按钮
	 */
	@FindBy(id = "com.tude.android:id/img_back")
	private WebElement m_back;
	
	/**
	 * 男装-TEE H5
	 */
	@FindBy(xpath = "//*[@id='pageWrap']/section/div/ul/li[1]/a/img")
	private WebElement m_tee_h5; 
	
	/**
	 * 3D模型 Navtive
	 */
	@FindBy(className="com.tencent.smtt.webkit.WebView")
	private WebElement m_3dModel_native;
	
	/**
	 * 3D模型 H5
	 */
	@FindBy(xpath = "//*[@id='canvas_3d']")//*[@id="canvas_3d"]
	private WebElement m_3dModel_h5; // 这个找不到
	
	/**
	 * 去购买 按钮
	 */
	@FindBy(id = "com.tude.android:id/btn_buy")
	private WebElement m_btn_buy; // 去购买

	public void buyMenWear() throws InterruptedException {
		
		try {
			
			if (!Helper.isActivityDisplayed(ActivityList.HOME_ACTIVITY)) {
				assertEquals(mdriver.currentActivity(), ActivityList.HOME_ACTIVITY);
			}

			m_sdv_image.get(1).click();// 点击：定制-男装

			// 进入男装二级目录，包含：TEE，边框TEE，Polo衫等…
			if (!Helper.isActivityDisplayed(ActivityList.PRODUCT_CLASSIFITION_ACTIVITY)) {
				assertEquals(mdriver.currentActivity(), ActivityList.PRODUCT_CLASSIFITION_ACTIVITY);
			}
			
			Helper.switchToWebView();// 切换到webview
			Thread.sleep(2000);
			m_tee_h5.click();// 点击：男装-TEE
			
			// 进入3D模型页面
			if (!Helper.isActivityDisplayed(ActivityList.GOODS_WEB3DVIEW_PAGER_ACTIVITY)) {
				assertEquals(mdriver.currentActivity(), ActivityList.GOODS_WEB3DVIEW_PAGER_ACTIVITY);
			}

			Helper.switchToNative();
			Thread.sleep(3000);
			System.out.println("sleep 3 seconds…");
			System.out.println("a:"+m_3dModel_native.getSize());
			System.out.println("b:"+m_3dModel_native.getLocation());// 元素的左上角点坐标
//			System.out.println("c:"+m_3dModel_native.getRect());// 不适用 android
									
			if (m_btn_buy.isDisplayed()) {
				m_btn_buy.click();
			}	
			
		} catch (Exception e) {
			assertTrue(false);
			e.printStackTrace();
		}

	}

}
