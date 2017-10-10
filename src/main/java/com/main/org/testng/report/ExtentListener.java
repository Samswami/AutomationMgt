//package com.main.org.testng.report;
//
//
//import framework.dataprovider.DriverDataProvider;
//import com.relevantcodes.extentreports.*;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.IClass;
//import org.testng.ITestContext;
//import org.testng.ITestResult;
//import org.testng.annotations.Factory;
//import org.testng.internal.IResultListener;
//import org.apache.commons.io.FileUtils;
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.*;
//
//public class ExtentListener implements IResultListener {
//
//	private ExtentReports reporter = new ExtentReports(ExtentReportConstants.EXTENT_REPORT_PATH, true,
//			DisplayOrder.NEWEST_FIRST, NetworkMode.OFFLINE, Locale.ENGLISH);;
//
//	private ExtentTest testReporter;
//	/* private WebDriver driver; */
//	private WebDriver driver;
//	
//	@Factory(dataProvider = DriverDataProvider.DRIVERPROVIDER, dataProviderClass = DriverDataProvider.class)
//	public ExtentListener(WebDriver driver) {
//		this.driver = driver;
//	}
//	
//	public void onStart(ITestContext arg0) {
//		reporter.loadConfig(new File(ExtentReportConstants.EXTENT_REPORT_CONFIG));
//
//	}
//
//	public void onTestStart(ITestResult result) {
//		testReporter = reporter.startTest(
//				getLabel(getClassName(result.getTestClass().getName()) + "." + result.getMethod().getMethodName()),
//				getLabelColor("Package Name := " + result.getTestClass().getName(), "LimeGreen"));
//
//		testReporter.log(LogStatus.INFO, "Starting test " + result.getMethod().getMethodName());
//
//	}
//
//	public void onTestSuccess(ITestResult result) {
//		
//		
//		String imgPath = System.currentTimeMillis() + result.getMethod().getMethodName();
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		IClass classObj1 = result.getTestClass();
//		logParameters(getFieldParameters(classObj1.getRealClass().getFields(), classObj1.getRealClass()), "pass");
//		System.out.println(">>>>>>>>>>>>->>>>>>>>>---------- IMG NAME " + ExtentReportConstants.SCREEN_SHOT_PATH +imgPath + ExtentReportConstants.PIC_EXTENSTION);
//		try {
//			FileUtils.copyFile(scrFile, new File(ExtentReportConstants.SCREEN_SHOT_PATH + imgPath +ExtentReportConstants.PIC_EXTENSTION));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		testReporter.log(LogStatus.PASS,
//				"Screenshot:" + testReporter.addScreenCapture(ExtentReportConstants.SCREEN_SHOT_RELATIVE_PATH + imgPath +ExtentReportConstants.PIC_EXTENSTION));
//		
//		
//		testReporter.log(LogStatus.INFO, "Parameters = " + getParams(result.getParameters()));
//		testReporter.log(LogStatus.PASS, "Test PASSED");
//		IClass classObj = result.getTestClass();
//		logParameters(getFieldParameters(classObj.getRealClass().getFields(), classObj.getRealClass()), "pass");
//		testReporter.log(LogStatus.INFO, "End");
//		reporter.endTest(testReporter);
//		reporter.flush();
//	}
//
//	public void onTestFailure(ITestResult result) {
//
//		/*
//		 * DriverInitialisation d = new DriverInitialisation(); this.driver =
//		 * d.getDriver();
//		 */
//
//		String imgPath = System.currentTimeMillis() + result.getMethod().getMethodName();
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		IClass classObj = result.getTestClass();
//		logParameters(getFieldParameters(classObj.getRealClass().getFields(), classObj.getRealClass()), "fail");
//		System.out.println(">>>>>>>>>>>>->>>>>>>>>---------- IMG NAME " + ExtentReportConstants.SCREEN_SHOT_PATH +imgPath + ExtentReportConstants.PIC_EXTENSTION);
//		try {
//			FileUtils.copyFile(scrFile, new File(ExtentReportConstants.SCREEN_SHOT_PATH + imgPath +ExtentReportConstants.PIC_EXTENSTION));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		testReporter.log(LogStatus.FATAL, "Parameters = " + getParams(result.getParameters()));
//		testReporter.log(LogStatus.FAIL, "Test Failed");
//		
//		testReporter.log(LogStatus.FAIL, result.getThrowable());
//		// if(null!=result.getAttribute("screencap") &&
//		// Boolean.parseBoolean(System.getProperty("JBuild")) )
//		testReporter.log(LogStatus.FATAL,
//				"Screenshot:" + testReporter.addScreenCapture(ExtentReportConstants.SCREEN_SHOT_RELATIVE_PATH + imgPath +ExtentReportConstants.PIC_EXTENSTION));
//		// else
//		// testReporter.log(LogStatus.FATAL,"Screenshot:" +
//		// testReporter.addScreenCapture((null==result.getAttribute("screencap")?"":result.getAttribute("screencap").toString())));
//		/*
//		 * testReporter.log(LogStatus.FAIL,
//		 * testReporter.addScreenCapture(ScreenShot.capture(driver)));
//		 */
//		testReporter.log(LogStatus.INFO, "End");
//		reporter.endTest(testReporter);
//		reporter.flush();
//	}
//
//	public void onTestSkipped(ITestResult result) {
////		testReporter.log(LogStatus.SKIP, "Parameters = " + getParams(result.getParameters()));
//		testReporter.log(LogStatus.SKIP, "Test Skipped");
//		IClass classObj = result.getTestClass();
//		logParameters(getFieldParameters(classObj.getRealClass().getFields(), classObj.getRealClass()), "pass");
//		testReporter.log(LogStatus.SKIP, result.getThrowable());
//		testReporter.log(LogStatus.INFO, "End");
//		reporter.endTest(testReporter);
//		reporter.flush();
//	}
//
//	public void onFinish(ITestContext context) {
//		reporter.endTest(testReporter);
//		reporter.flush();
//		reporter.close();
//	}
//
//	/*---------------- custom methods ---------------------*/
//
//	public String getClassName(String packageName) {
//		String[] splitPackageName = packageName.split("\\.", -1);
//		return splitPackageName[(splitPackageName.length) - 1];
//	}
//
//	public String getLabel(String text) {
//		return "<span class='label outline info'>" + text + "</span>";
//	}
//
//	public String getLabelColor(String text, String color) {
//		return "<span class='label outline info' style='font-size:150%;color:" + color + ";font-weight:bold'>" + text
//				+ "</span>";
//	}
//
//	public List<Object> getParams(Object[] object) {
//		List<Object> paramObject = new ArrayList<Object>();
//		for (Object obj : object) {
//			paramObject.add(obj);
//		}
//			return paramObject;
//	}
//
//	public HashMap<String, String> getFieldParameters(Field[] fields, Object obj) {
//		HashMap<String, String> fieldMap = new HashMap<String, String>();
//		for (Field field : fields) {
//			try {
//				try {
//					if( (field.get(obj).toString() == null) || (field.get(obj).toString().isEmpty()) ) continue;
//					fieldMap.put(field.getName(), field.get(obj).toString());
//					System.out.println("Field =" + field.getName() + "" + field.get(obj));
//				} catch (IllegalAccessException e) {
//				}
//			} catch (IllegalArgumentException e) {
//			}
//		}
//		return fieldMap;
//	}
//
//	public void logParameters(HashMap<String, String> fields, String status) {
//		if (status.toLowerCase().equals("pass")) {
//			for (Map.Entry<String, String> field : fields.entrySet()) {
//				testReporter.log(LogStatus.PASS, field.getKey() + " = " + field.getValue());
//			}
//		} else if (status.toLowerCase().equals("fail")) {
//			for (Map.Entry<String, String> field : fields.entrySet()) {
//				testReporter.log(LogStatus.FAIL, field.getKey() + " = " + field.getValue());
//			}
//		} else if (status.toLowerCase().equals("skip")) {
//			for (Map.Entry<String, String> field : fields.entrySet()) {
//				testReporter.log(LogStatus.SKIP, field.getKey() + " = " + field.getValue());
//			}
//		}
//	}
//
//	/*---------------- END ---------------------*/
//
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		// TODO Auto-generated method stub
//	}
//
//	public void onConfigurationFailure(ITestResult arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void onConfigurationSkip(ITestResult arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void onConfigurationSuccess(ITestResult arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//}