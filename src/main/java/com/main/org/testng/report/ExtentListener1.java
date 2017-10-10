package com.main.org.testng.report;

import com.main.org.testng.constants.TestNG_Constants;
import com.relevantcodes.extentreports.*;

import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.IResultListener;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ExtentListener1 implements IResultListener {

	private ExtentReports reporter = new ExtentReports(
			TestNG_Constants.EXTENT_REPORT_PATH + TestNG_Constants.EXTENT_REPORT_NAME, true, DisplayOrder.NEWEST_FIRST,
			NetworkMode.OFFLINE, Locale.ENGLISH);
	private ExtentTest testReporter;

	public void onStart(ITestContext arg0) {
		reporter.loadConfig(new File(TestNG_Constants.EXTENT_REPORT_CONFIG_FILE));
	}

	public void onTestStart(ITestResult result) {
		testReporter = reporter.startTest(
				getLabel(getClassName(result.getTestClass().getName()) + "." + result.getMethod().getMethodName()),
				getLabelColor("Package Name := " + result.getTestClass().getName(), "LimeGreen"));

		testReporter.log(LogStatus.INFO, "Starting test " + result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {
		testReporter.log(LogStatus.PASS, "Parameters = " + getParams(result.getParameters()));
		testReporter.log(LogStatus.PASS, "Test PASSED");
		IClass classObj = result.getTestClass();
		logParameters(getFieldParameters(classObj.getRealClass().getDeclaredFields(), classObj.getRealClass()),"pass");
		testReporter.log(LogStatus.INFO, "End");
		reporter.endTest(testReporter);
		reporter.flush();
	}

	public void onTestFailure(ITestResult result) {
		testReporter.log(LogStatus.FAIL, "Parameters = " + getParams(result.getParameters()));
		testReporter.log(LogStatus.FAIL, "Test Failed");
		IClass classObj = result.getTestClass();
		logParameters(getFieldParameters(classObj.getRealClass().getFields(), classObj.getRealClass()),"fail");
		testReporter.log(LogStatus.FAIL, result.getThrowable());
		testReporter.log(LogStatus.INFO, "End");
		reporter.endTest(testReporter);
		reporter.flush();
	}

	public void onTestSkipped(ITestResult result) {
		testReporter.log(LogStatus.SKIP, "Parameters = " + getParams(result.getParameters()));
		testReporter.log(LogStatus.SKIP, "Test Skipped");
		IClass classObj = result.getTestClass();
		logParameters(getFieldParameters(classObj.getRealClass().getFields(), classObj.getRealClass()),"skip");
		testReporter.log(LogStatus.SKIP, result.getThrowable());
		testReporter.log(LogStatus.INFO, "End");
		reporter.endTest(testReporter);
		reporter.flush();
	}

	public void onFinish(ITestContext context) {
		reporter.endTest(testReporter);
		reporter.flush();
		reporter.close();
	}

	/*---------------- custom methods ---------------------*/

	public String getClassName(String packageName) {
		String[] splitPackageName = packageName.split("\\.", -1);
		return splitPackageName[(splitPackageName.length) - 1];
	}

	public String getLabel(String text) {
		return "<span class='label outline info'>" + text + "</span>";
	}

	public String getLabelColor(String text, String color) {
		return "<span class='label outline info' style='font-size:150%;color:" + color + ";font-weight:bold'>" + text
				+ "</span>";
	}

	public List<Object> getParams(Object[] object) {
		List<Object> paramObject = new ArrayList<Object>();
		for (Object obj : object) {
			paramObject.add(obj);
		}
		return paramObject;
	}

	public HashMap<String, String> getFieldParameters(Field[] fields, Object obj) {
		HashMap<String, String> fieldMap = new HashMap<String, String>();
		for (Field field : fields) {
			try {
				try {
					System.out.println(field.getName());
					System.out.println(field.get(obj));
					fieldMap.put(field.getName(), field.get(obj).toString());
				} catch (IllegalAccessException e) {
				}
			} catch (IllegalArgumentException e) {
			}catch(NullPointerException e){
				
			}
		}
		return fieldMap;
	}

	public void logParameters(HashMap<String, String> fields, String status) {
		if (status.toLowerCase().equals("pass")) {
			for (Map.Entry<String, String> field : fields.entrySet()) {
				testReporter.log(LogStatus.PASS, field.getKey() + " = " + field.getValue());
			}
		} else if (status.toLowerCase().equals("fail")) {
			for (Map.Entry<String, String> field : fields.entrySet()) {
				testReporter.log(LogStatus.FAIL, field.getKey() + " = " + field.getValue());
			}
		} else if (status.toLowerCase().equals("skip")) {
			for (Map.Entry<String, String> field : fields.entrySet()) {
				testReporter.log(LogStatus.SKIP, field.getKey() + " = " + field.getValue());
			}
		}
	}
	/*---------------- END ---------------------*/

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onConfigurationFailure(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onConfigurationSkip(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onConfigurationSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}
}