package com.main.org.service;


import com.main.org.testng.constants.DriverConstants;
import framework.dataprovider.DriverDataProvider;


public class DriverIniService {

	public DriverIniService(String browserName) {
		DriverDataProvider.driverInitialisation(browserName);
		DriverDataProvider.navigateTo(DriverConstants.PATH);
	}
	
/*	public static void main(String[] args) {
		DriverDataProvider.driverInitialisation("chrome");
		DriverDataProvider.navigateTo("http://localhost:8080/login?from=%2F");
		WebElementUtil wbele = WebElementUtil.getInstance(DriverDataProvider.getDriverInstance());
		wbele.clickElementById("security-token");
		wbele.getElementById("security-token").sendKeys("abc");
//		WebDriverWait w = new WebDriverWait(DriverDataProvider.getDriverInstance(), 1);
	}*/
	
}
