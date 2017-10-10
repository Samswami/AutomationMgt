package com.main.org.entity;

import java.util.Arrays;

public class RunEntity {

	String parallel;
	String runName;
	String runDiscription;
	String browser;
	int[] testIdToRun;
	
	public RunEntity(String parallel, String runName, String runDiscription, String browser, int[] testIdToRun) {
		super();
		this.parallel = parallel;
		this.runName = runName;
		this.runDiscription = runDiscription;
		this.browser = browser;
		this.testIdToRun = testIdToRun;
	}
	public RunEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getParallel() {
		return parallel;
	}
	public void setParallel(String parallel) {
		this.parallel = parallel;
	}
	public String getRunName() {
		return runName;
	}
	public void setRunName(String runName) {
		this.runName = runName;
	}
	public String getRunDiscription() {
		return runDiscription;
	}
	public void setRunDiscription(String runDiscription) {
		this.runDiscription = runDiscription;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public int[] getTestIdToRun() {
		return testIdToRun;
	}
	public void setTestIdToRun(int[] testIdToRun) {
		this.testIdToRun = testIdToRun;
	}
	
	@Override
	public String toString() {
		return "Run Entity Parameters { " +
				" Browser = "+browser+
				", Name = "+ runName +
				", Discription = "+runDiscription+
				", Parallel = "+ parallel+
				", TestId's = "+ Arrays.toString(testIdToRun)+
				"}";
	}
	
}
