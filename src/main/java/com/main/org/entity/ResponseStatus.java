package com.main.org.entity;

public class ResponseStatus {

	private String status;
	private int statusCode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public ResponseStatus() {
	}

	public ResponseStatus(String status, int statusCode) {
		this.status = status;
		this.statusCode = statusCode;
	}

}
