package top.play.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import top.base.appium.Helper;
import top.base.utils.ImageUtil;

public class DIYPage {

	private AndroidDriver<MobileElement> mdriver;

	public DIYPage() {
		// TODO Auto-generated constructor stub
	}

	public DIYPage(AndroidDriver<MobileElement> driver) {
		this.mdriver = driver;
	}

	/**
	 * 定制 按钮
	 */
	@FindBy(id = "com.play.android:id/btn_diy")
	private WebElement m_btn_diy;// 定制

	@FindBy(id = "com.play.android:id/iv_item")
	private List<MobileElement> m_plate_list;

	/**
	 * 定制页面-产品list
	 */
	@FindBy(id = "com.play.android:id/sdv_image")
	private List<WebElement> m_sdv_image;

	@FindBy(className = "com.tencent.smtt.webkit.WebView")
	private WebElement m_tee_native;

	/**
	 * 男装-TEE H5
	 */
	@FindBy(xpath = "//*[@id='pageWrap']/section/div/ul/li[1]/a/img")
	//*[@id="pageWrap"]/section/div/ul/li[1]/a/img
	//*[@id="pageWrap"]/section/div/ul/li[2]/a/img
	//*[@id="pageWrap"]/section/div/ul/li[4]/a/img
	
	private WebElement m_tee_h5;

	/**
	 * 3D模型 Navtive
	 */
	@FindBy(className = "com.tencent.smtt.webkit.WebView")
	private WebElement m_3dModel_native;

	/**
	 * 3D模型 H5
	 */
	@FindBy(xpath = "//*[@id='canvas_3d']") // *[@id="canvas_3d"]
	private WebElement m_3dModel_h5; // 这个找不到

	/**
	 * 3D模型 模板选择
	 */
	@FindBy(id = "com.play.android:id/iv_good")
	private List<WebElement> m_iv_good;

	/**
	 * 去购买 按钮
	 */
	@FindBy(id = "com.play.android:id/btn_buy")
	private WebElement m_btn_buy; // 去购买

	/**
	 * 进入3d模型界面
	 */
	public void goTo3DModel() {

		Helper.setDriver(mdriver);

		if (!Helper.isActivityDisplayed(ActivityList.HOME_ACTIVITY)) {
			assertEquals(mdriver.currentActivity(), ActivityList.HOME_ACTIVITY);
		}

		if (!(m_sdv_image.size() > 0)) {
			assertTrue(false, "首页产品List，没有加载成功");
		}
		
		m_sdv_image.get(1).click();// 点击：定制-男装

		// 进入男装二级目录，包含：TEE，边框TEE，Polo衫等…
		if (!Helper.isActivityDisplayed(ActivityList.PRODUCT_CLASSIFITION_ACTIVITY)) {
			assertEquals(mdriver.currentActivity(), ActivityList.PRODUCT_CLASSIFITION_ACTIVITY,"没有打开：男装的二级目录");
		}

		if (!m_tee_native.isDisplayed()) {
			assertTrue(false, "没有定位到：男装目录下的webview");
		}
		
/*		// 获取控件的宽度 和 高度
		int x = m_tee_native.getSize().width;
		int y = m_tee_native.getSize().height;
		int n = m_tee_native.getLocation().y;// 起始点的y坐标

		mdriver.tap(1, x / 2, y / 6 + n, 500);// 点击：男装-TEE 的坐标
*/		
		Helper.switchToWebView();
		m_tee_h5.click();// 点击：男装-TEE
		
		// 进入3D模型页面
		if (!Helper.isActivityDisplayed(ActivityList.GOODS_WEB3DVIEW_PAGER_ACTIVITY)) {
			assertEquals(mdriver.currentActivity(), ActivityList.GOODS_WEB3DVIEW_PAGER_ACTIVITY,
					"没有打开GoodsWeb3dViewPagerActivity页面");
		}
		
		Helper.switchToNative();
		if (!m_3dModel_native.isDisplayed()) {
			assertTrue(false, "3D模型没有加载出来！");
		}
	}

	/**
	 * 检查 3d模型能否正常加载
	 * 
	 */
	public void check3DModelView() {

		try {
			
			this.goTo3DModel();

			if (m_3dModel_native.isDisplayed()) {

				String screenName = "man_tee_blank.jpg";
				Helper.takeScreenShot(screenName);

				File imgA = new File(Constant.ACTUL_PATH, screenName);// 生成的文件
				File imgB = new File(Constant.EXPECTED_PATH, Constant.MAN_TEE_BLANK_EXP);// 预留的文件
				System.out.println("两张图片的实际相似度：" + ImageUtil.getSamePercentFrom(imgA, imgB));

				if (!ImageUtil.sameAs(imgA, imgB, 0.8)) {

					Reporter.log("实际的图片：");
					Reporter.log("<img src=" + Constant.ACTUL_PATH + screenName + " /img>", true);// 将截图显示在报告中

					Reporter.log("预期的图片：");
					Reporter.log("<img src=" + Constant.EXPECTED_PATH + Constant.MAN_TEE_BLANK_EXP + " /img>", true);// 将截图显示在报告中
					// assertTrue(false, "两张图片的相似度不足80%");
				}
				;
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}

	}

	/**
	 * 遍历所有商品
	 * @throws InterruptedException 
	 */
	public void showAllGoods() throws InterruptedException {

		if (!Helper.isActivityDisplayed(ActivityList.GOODS_WEB3DVIEW_PAGER_ACTIVITY)) {
			assertEquals(mdriver.currentActivity(), ActivityList.GOODS_WEB3DVIEW_PAGER_ACTIVITY,
					"没有打开GoodsWeb3dViewPagerActivity页面");
		}

		if (!m_3dModel_native.isDisplayed()) {
			assertTrue(false, "m_3dModel_native 没有定位到");
		}

		System.out.println("m_iv_good.size():" + m_iv_good.size());
		
		for (int i = 0; i < m_iv_good.size(); i++) {
			
			m_iv_good.get(i).click();
			
			if (!m_3dModel_native.isDisplayed()) {
				assertTrue(false, "没有定位到 m_3dModel_native");
			}
			
			if (i == m_iv_good.size()-1) {
				
				TouchAction action = new TouchAction(mdriver);
				action.press(m_iv_good.get(m_iv_good.size()-1)).waitAction(800).moveTo(m_iv_good.get(0)).release().perform();
				System.out.println("222222");

				Thread.sleep(2000);
			}
			
			System.out.println(i+" is OK");

		}

	}

}
