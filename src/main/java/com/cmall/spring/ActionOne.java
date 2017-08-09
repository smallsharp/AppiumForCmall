package com.cmall.spring;

import static org.testng.Assert.assertEquals;
import org.apache.log4j.Logger;

public class ActionOne {
	
	private Logger log = Logger.getLogger(ActionOne.class);
	private DriverHelper dHelper;
	
	public void setDHelper(DriverHelper dHelper) {
		this.dHelper = dHelper;
	}

	public void login() {
		log.info("-------开始测试-------");
		try {
			dHelper.clickonElement(dHelper.findElementById(IDFactory.ID_MY));
			dHelper.clickonElement(dHelper.findElementById(IDFactory.TV_ACCOUNT));
			dHelper.sendKeys(dHelper.findElementById(IDFactory.ET_ACCOUNT), "18521035133");
			dHelper.sendKeys(dHelper.findElementById(IDFactory.ET_PASSWORD), "111111");
			dHelper.clickonElement(dHelper.findElementById(IDFactory.BTN_LOGIN));
			if (!dHelper.waitActivity(IActivities.HOME_ACTIVITY)) {
				assertEquals(dHelper.getCurrentActivity(), IActivities.HOME_ACTIVITY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("-------结束测试-------");
	}
	
	public void logout() {
		log.info("-------开始测试-------");
		
		if (!dHelper.waitActivity(IActivities.HOME_ACTIVITY)) {
			log.error("不在HOME_ACTIVITY，当前页面：" + dHelper.getCurrentActivity());
			return;
		}
		try {
			dHelper.clickonElement(dHelper.findElementById(IDFactory.ID_MY));
			dHelper.clickonElement(dHelper.findElementById(IDFactory.BTN_LOGOUT));
			dHelper.clickonElement(dHelper.findElementById(IDFactory.QUIT));
			if (!dHelper.waitActivity(IActivities.HOME_ACTIVITY)) {
				assertEquals(dHelper.getCurrentActivity(), IActivities.HOME_ACTIVITY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("-------结束测试-------");
	}

	
}
