package com.main.org.testng.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listeners")
public class Listeners {

	private List<Listener> listener;
	
	@XmlElement
	public List<Listener> getListener() {
		return listener;
	}

	public void setListener(List<Listener> listener) {
		this.listener = listener;
	}

	public Listeners(List<Listener> listener) {
		this.listener = listener;
	}

	public Listeners() {
	}
	
}
