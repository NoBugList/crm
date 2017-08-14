package com.shsxt.crm.exception;

@SuppressWarnings("serial")
public class ParamException extends RuntimeException {
	
	private Integer errorCode;
	
	public ParamException() {
		
	}
	
	public ParamException(String message) {
		super(message);
		this.errorCode = 0;
	}
	
	public ParamException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
}
