package com.main.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.main.org.entity.ResponseStatus;
import com.main.org.entity.RunEntity;
import com.main.org.entity.TestMethod;
import com.main.org.service.DriverIniService;
import com.main.org.service.TestMethodService;
import com.main.org.testng.runner.TestNG_Runner;


@RestController
public class TestMethodController {

	@Autowired
	TestMethodService testMethodService;
	@Autowired
	TestNG_Runner testNGRunner;
	boolean status;

	@RequestMapping(value = "/addTestMethod", method = RequestMethod.POST)
	public ResponseStatus addTestMethod(@RequestBody TestMethod testMethod) {
		if(!(testMethod.getTestMethodName().equals(""))){
		if (testMethodService.addTestMethod(testMethod)) {
			return new ResponseStatus("Successfully Added",0);
		} else
			return new ResponseStatus("Duplicate Name exists",1);
		}else{
			return new ResponseStatus("Name field cannot be blank",2);
		}
	}

	@RequestMapping(value = "/removeTestMethod", method = RequestMethod.DELETE)
	public ResponseStatus removeTestMetho(@RequestBody int[] testMethodId) {
		if(testMethodId.length != 0){
		testMethodService.deleteTestMethod(testMethodId);
		return new ResponseStatus("Successfully deleted",0);
		}else{
			return new ResponseStatus("Nothing Selected to delete",2);
		}
	}

	@RequestMapping(value = "/updateTestMethod", method = RequestMethod.PUT)
	public ResponseStatus updateTestMethod(@RequestBody TestMethod testMethod) {
		System.out.println("update = "+testMethod);
		if(testMethod.getTestMethodId() != 0){
		if (testMethodService.updateTestMethod(testMethod)) {
			return new ResponseStatus("Successfully updated",0);
		} else
			return new ResponseStatus("Nothing to update",3);
		} else return new ResponseStatus("Nothing Selected to update",2);
	}

	@RequestMapping(value = "/runMethodList", method = RequestMethod.POST)
	public ResponseStatus runApi(@RequestBody RunEntity runParameter) throws InterruptedException  {
		
		try{
		if(!status){
			int[] testMethodId = runParameter.getTestIdToRun();
			new DriverIniService(runParameter.getBrowser());
			status = true;
		if (testMethodId.length != 0) {
			List<String> testMethodNames = testMethodService.createListOfData(testMethodId);
			 testNGRunner.executeTestNG_XMLAndGenReport(testMethodNames,runParameter.getRunName());
			status = false;
			return new ResponseStatus("Success", 0);
		} 		else {
			status = false;
			return new ResponseStatus("Nothing selected to run!", 2);
		}
		}else{
			return new ResponseStatus("Already Running job", 3);
		}
		}catch(Exception e){
			e.printStackTrace();
			status = false;
			return new ResponseStatus("Failed", 1);
		}
	}

	/*@RequestMapping(value = "/runMethodList", method = RequestMethod.POST)
	public ResponseStatus runApi(@RequestBody RunEntity runParameter ) throws InterruptedException  {
		System.out.println(runParameter);
			return new ResponseStatus("Success", 0);
		
	}*/
	
	@RequestMapping("/getMethodList")
	public List<TestMethod> getAllTestMethod() {
		return testMethodService.getAllTestMethod();
	}

//	@RequestMapping(value = "/test")
//	public String sample() {
//		TestMethod tm = new TestMethod("sam", "fdsjk");
//		testMethodService.addTestMethod(tm);
//		TestMethod tm1 = new TestMethod("asdsfsam", "fdsdfsdfsjk");
//		testMethodService.addTestMethod(tm1);
//		return "Success";
//	}

}
