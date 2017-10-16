package com.main.org.service;


//import com.main.org.testng.constants.DriverConstants;
import framework.dataprovider.DriverDataProvider;


public class DriverIniService {

	public DriverIniService(String browserName) {
		DriverDataProvider.driverInitialisation(browserName);
		/*DriverDataProvider.navigateTo(DriverConstants.PATH);*/
	}
	
}
