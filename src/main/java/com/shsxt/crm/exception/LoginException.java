package com.shsxt.crm.exception;

@SuppressWarnings("serial")
public class LoginException extends RuntimeException {
	
	private int errorCode;
	
	public LoginException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public LoginException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
	
}
