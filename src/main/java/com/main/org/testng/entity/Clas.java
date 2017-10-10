package com.main.org.testng.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Clas {

	
	private String name;

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Clas() {

	}

	public Clas(String name) {
		super();
		this.name = name;
	}

}
