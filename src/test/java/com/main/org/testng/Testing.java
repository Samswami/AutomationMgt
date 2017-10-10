package com.main.org.testng;

import java.io.IOException;
import java.util.*;
import com.main.org.testng.entity.*;
import com.main.org.testng.fileutil.FileUtil;
import com.main.org.testng.runner.TestNG_Runner;

public class Testing {

	static TestNG_Runner runner;
	static FileUtil fileUtil;

	public static void main(String[] args) throws IOException {
		initialise();
		runner.createTestNG_XML(createListOfClas());
		
		// fileUtil.cleanUpFolder();

	}

	public static List<String> createListOfClas() {
		List<String> listOfClas = new ArrayList<String>();
		listOfClas.add("SamClass1");
		listOfClas.add("SamClass1");
		return listOfClas;
	}

	public static List<Classes> createListOfClasses(Classes classes) {
		List<Classes> listOfClasses = new ArrayList<Classes>();
		listOfClasses.add(classes);
		return listOfClasses;
	}

	public static List<Test> createListOfTest(Test test) {
		List<Test> listOfTest = new ArrayList<Test>();
		listOfTest.add(test);
		return listOfTest;
	}

	public static void initialise() {
		runner = new TestNG_Runner();
		fileUtil = new FileUtil();
	}
}
