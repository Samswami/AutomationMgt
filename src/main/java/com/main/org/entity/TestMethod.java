package com.main.org.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TestMethod {
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private int testMethodId;
	private String testMethodName;
	private String testMethodDescription;

	public TestMethod() {
	}

	public TestMethod(String testMethodName, String testMethodDescription) {
		this.testMethodName = testMethodName;
		this.testMethodDescription = testMethodDescription;
	}

	public String getTestMethodDescription() {
		return testMethodDescription;
	}

	public void setTestMethodDescription(String testMethodDescription) {
		this.testMethodDescription = testMethodDescription;
	}

	public String getTestMethodName() {
		return testMethodName;
	}

	public void setTestMethodName(String testMethodName) {
		this.testMethodName = testMethodName;
	}

	public int getTestMethodId() {
		return testMethodId;
	}

	public void setTestMethodId(int testMethodId) {
		this.testMethodId = testMethodId;
	}

	@Override
	public String toString() {
		return "TestMethod{" + 
				"Id = " + testMethodId + ", Name = " + testMethodName + ", Description = "+ testMethodDescription 
				+ "}";
	}
}
