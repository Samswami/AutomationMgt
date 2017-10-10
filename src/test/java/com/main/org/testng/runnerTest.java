package com.main.org.testng;

import java.util.*;
import org.testng.TestNG;

public class runnerTest {
	public static void main(String[] args) {
		executeTestNG("testng.xml");
	}
	public static void executeTestNG(String path){
		TestNG testNG = new TestNG();
		List<String> suiteFiles = new ArrayList<String>();
		suiteFiles.add(path);
		testNG.setTestSuites(suiteFiles);
		testNG.run();
	}
}
