package com.main.org.testng.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listener")
public class Listener {

	private String className;

	@XmlAttribute(name = "class-name")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Listener(String className) {
		super();
		this.className = className;
	}

	public Listener() {
	}
	
}
