package com.edj.activity.demo.exception;

public class ErrorResponse {

	String message;
	int errodCode;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getErrodCode() {
		return errodCode;
	}
	public void setErrodCode(int errodCode) {
		this.errodCode = errodCode;
	}
	public ErrorResponse(String message, int errodCode) {
		super();
		this.message = message;
		this.errodCode = errodCode;
	}
	public ErrorResponse() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
