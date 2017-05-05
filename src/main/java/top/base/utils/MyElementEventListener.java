package top.baseutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.events.api.general.AppiumWebDriverEventListener;


public class MyDriverListener implements AppiumWebDriverEventListener {

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
    	System.out.println("出异常了~~~~~");
/*        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String dateString = format.format(new Date());

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try{
            File screenShot = new File("D:\\"+dateString+".png");
            FileUtils.copyFile(srcFile, screenShot);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("发生异变,原因是: "+throwable.getMessage());
        System.out.println("截图保存在: "+"D:\\"+dateString+".png");*/

    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("afterNavigateTo: "+url);
        System.out.println("afterNavigateTo by driver: "+driver.getCurrentUrl());
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println("单机页面元素的属性: "+element.getAttribute("value"));

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("查找元素的条件是: "+ by.toString());

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        // TODO Auto-generated method stub

    }


    @Override
    public void afterScript(String url, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        // TODO Auto-generated method stub

    }


    @Override
    public void beforeNavigateBack(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("beforeNavigateTo: "+url);
        System.out.println("beforeNavigateTo by driver : "+driver.getCurrentUrl());
    }

    @Override
    public void beforeScript(String url, WebDriver driver) {
        // TODO Auto-generated method stub

    }
}