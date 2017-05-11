package top.cases;

import org.testng.annotations.Test;
import top.base.utils.Assist;
import top.base.utils.MyBaseCase;

public class Slide_Test extends MyBaseCase{
	
	@Test
	public void test() throws InterruptedException{
		if (Assist.waitForActivity(".activity.main.NativeVideoActivity")) {
			Assist.findElementById("com.tude.android:id/btn_jump").click();
		}
		
		new Assist().slideUP();
		
		Thread.sleep(2000);
	}

}
