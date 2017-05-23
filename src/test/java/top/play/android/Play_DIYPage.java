package top.play.android;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import io.appium.java_client.android.AndroidDriver;
import top.base.utils.Helper;
import top.base.utils.ImageUtil;
public class Play_DIYPage {
	
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
	 * 3D模型 模板选择
	 */
	private List<WebElement> m_3d_selector;
	
	/**
	 * 去购买 按钮
	 */
	@FindBy(id = "com.tude.android:id/btn_buy")
	private WebElement m_btn_buy; // 去购买
	
	private AndroidDriver<WebElement> mdriver;

	public void buyMenWear() throws InterruptedException {
		
		try {
			
			mdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			if (!Helper.isActivityDisplayed(ActivityList.HOME_ACTIVITY)) {
				assertEquals(mdriver.currentActivity(), ActivityList.HOME_ACTIVITY);
			}
			
			if (m_sdv_image.size() < 0) {
				assertTrue(false, "首页产品List，没有加载成功");
			}
			m_sdv_image.get(1).click();// 点击：定制-男装

			// 进入男装二级目录，包含：TEE，边框TEE，Polo衫等…
			if (!Helper.isActivityDisplayed(ActivityList.PRODUCT_CLASSIFITION_ACTIVITY)) {
				assertEquals(mdriver.currentActivity(), ActivityList.PRODUCT_CLASSIFITION_ACTIVITY);
			}
			
			Helper.switchToWebView();// 切换到webview
			m_tee_h5.click();// 点击：男装-TEE
			
			// 进入3D模型页面
			if (!Helper.isActivityDisplayed(ActivityList.GOODS_WEB3DVIEW_PAGER_ACTIVITY)) {
				assertEquals(mdriver.currentActivity(), ActivityList.GOODS_WEB3DVIEW_PAGER_ACTIVITY);
			}

			Helper.switchToNative();
			
			if (m_3dModel_native.isDisplayed()) {
				System.out.println("a:"+m_3dModel_native.getSize());
				System.out.println("b:"+m_3dModel_native.getLocation());// 元素的左上角点坐标
				
				String screenName = "man_tee_blank.jpg";
				Helper.takeScreenShot(screenName);
				
				File imgA = new File(Constant.ACTUL_PATH,screenName);// 生成的文件
				File imgB = new File(Constant.EXPECTED_PATH,Constant.MAN_TEE_BLANK_EXP);// 预留的文件
				System.out.println("两张图片的实际相似度："+ ImageUtil.getSamePercentFrom(imgA, imgB));
				if (!ImageUtil.sameAs(imgA, imgB, 0.8)) {
					
					Reporter.log("实际的图片：");
					Reporter.log("<img src=" + Constant.ACTUL_PATH + screenName + " /img>", true);// 将截图显示在报告中
					
					Reporter.log("预期的图片：");
					Reporter.log("<img src=" + Constant.EXPECTED_PATH + Constant.MAN_TEE_BLANK_EXP + " /img>", true);// 将截图显示在报告中

					assertTrue(false, "两张图片的相似度不足80%");
				};
				
			}
			
			
			System.out.println("m_3d_selector.size():" + m_3d_selector.size());
			
			for (int i = 1; i < m_3d_selector.size(); i++) {
				m_3d_selector.get(i).click();
				
				if (m_3dModel_native.isDisplayed()) {
					
					String screenName = "man_tee_"+i+".jpg";
					Helper.takeScreenShot(screenName);
					
					File imgA = new File(Constant.ACTUL_PATH,screenName);// 生成的文件
					File imgB = new File(Constant.EXPECTED_PATH,"man_tee_"+i+"exp.jpg");// 预留的文件
					System.out.println("两张图片的实际相似度："+ ImageUtil.getSamePercentFrom(imgA, imgB));
					if (!ImageUtil.sameAs(imgA, imgB, 0.8)) {
						
						Reporter.log("实际的图片：");
						Reporter.log("<img src=" + Constant.ACTUL_PATH + screenName + " /img>", true);// 将截图显示在报告中
						
						Reporter.log("预期的图片：");
						Reporter.log("<img src=" + Constant.EXPECTED_PATH + "man_tee_"+i+"exp.jpg" + " /img>", true);// 将截图显示在报告中

						assertTrue(false, "两张图片的相似度不足80%");
					};
				}
				
				
				
			}
			
			
			Thread.sleep(2000);
					
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}

	}

}
