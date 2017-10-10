package com.main.org.testng.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "test")
public class Test {

	
	private String name;
	
	private List<Classes> classes;

	public Test() {
	}

	public Test(String name, List<Classes> classes) {
		super();
		this.name = name;
		this.classes = classes;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public List<Classes> getClasses() {
		return classes;
	}

	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}

}
