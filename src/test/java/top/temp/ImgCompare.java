package top.temp;

import static org.junit.Assert.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import top.base.utils.CommandUtils;
import top.base.utils.ImageUtil;

public class ImgCompare {
	
	private AndroidDriver<?> driver;  
    
    @Before  
    public void setUp() throws Exception {  
    	System.out.println("setup");
        DesiredCapabilities cap = new DesiredCapabilities();  
        cap.setCapability("deviceName", "85GBBMA2353T");  
        cap.setCapability("appPackage", "com.tude.android");  
        cap.setCapability("appActivity", ".activity.SplashActivity");  
          
        driver = new AndroidDriver<WebElement>(new URL("http://192.168.101.201:4723/wd/hub"),cap);  
    }  
  
    @After  
    public void tearDown() throws Exception {
    	System.out.println("teardown");
		CommandUtils.exec_shell("pm clear com.tude.android");
    }  
  
    @Test  
    public void compareScreenAndSubScreen() throws InterruptedException, IOException{  
        Thread.sleep(2000);  
          
        driver.findElementById("com.tude.android:id/btn_jump").click();
        Thread.sleep(1000);  
          
        File screenShot1 = driver.getScreenshotAs(OutputType.FILE);  
        FileUtils.copyFile(screenShot1, new File("C:/a.png"));  
        BufferedImage img1 = ImageUtil.getImageFromFile(screenShot1);  
        
        System.out.println("11111111");
          
        File screenShot2 = driver.getScreenshotAs(OutputType.FILE);  
        FileUtils.copyFile(screenShot2, new File("C:/b.png"));  
        BufferedImage img2 = ImageUtil.getImageFromFile(screenShot2);  
        System.out.println("2222222");

        Boolean same = ImageUtil.sameAs(img1, img2, 0.9);  
        assertTrue(same);  
        System.out.println("3333333");

        BufferedImage subImg1 = ImageUtil.getSubImage(img1, 6, 39, 474, 38);  
        BufferedImage subImg2 = ImageUtil.getSubImage(img1, 6, 39, 474, 38);  
        same = ImageUtil.sameAs(subImg1, subImg2, 1);  
        System.out.println("444444444");

        File f3 = new File("c:/sub-1.png");  
        ImageIO.write(subImg1, "PNG", f3);  
          
        File f4 = new File("c:/sub-2.png");  
        ImageIO.write(subImg1, "PNG", f4);  
        System.out.println("5555555");

          
    }  

}
