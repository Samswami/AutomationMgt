package com.main.org.testng.entity;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "suite")
public class Suite {

	private String name;

	private List<Test> test;

	private Listeners listeners;

	public Suite() {
	}

	public Suite(String name, List<Test> test, Listeners listeners) {

		this.name = name;
		this.test = test;
		this.listeners = listeners;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public Listeners getListeners() {
		return listeners;
	}

	public void setListeners(Listeners listeners) {
		this.listeners = listeners;
	}

	@XmlElement
	public List<Test> getTest() {
		return test;
	}

	public void setTest(List<Test> test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "{SuiteName = " + name + ", ListOfTest = " + test + ", Listeners = " +listeners+  "}";
	}

}
