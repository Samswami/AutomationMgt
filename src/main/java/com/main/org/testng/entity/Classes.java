package com.main.org.testng.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "classes")
public class Classes {

	
	private List<Clas> clas;

	@XmlElement(name = "class")
	public List<Clas> getClas() {
		return clas;
	}

	public void setClas(List<Clas> clas) {
		this.clas = clas;
	}

	public Classes(List<Clas> clas) {
		super();
		this.clas = clas;
	}

	public Classes() {
	}

}
