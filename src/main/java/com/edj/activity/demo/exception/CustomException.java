package com.edj.activity.demo.exception;

public class CustomException extends Exception {

	private final int errorCode;
	
	public CustomException(String message, int errorCode){
		super(message);
		this.errorCode = errorCode;
	}
	
	public int getErrorCode() {
        return errorCode;
    }
}
