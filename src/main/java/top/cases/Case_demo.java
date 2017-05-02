package top.cases;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidKeyCode;
import top.baseutils.Driver;
import top.baseutils.MyAndroidDriver;
import top.baseutils.MyTestCase;

public class Case_demo extends MyTestCase{
	
	public static MyAndroidDriver<WebElement> mDriver = Driver.newInstance();

	
	@Test
	public void test(){
	List<WebElement> elements = mDriver.findElementsById("com.tude.android:id/sdv_image");
	
	System.out.println(elements.size());
	
	elements.get(0).click();
	System.out.println(mDriver.getContextHandles());
	mdriver.pressKeyCode(AndroidKeyCode.BACK);
	elements.get(1).click();
	
	assertTrue(false);
	mdriver.pressKeyCode(AndroidKeyCode.BACK);

	
	}

}
