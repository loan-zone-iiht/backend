package com.ibm.exception;

/**
 * Class {GlobalLoanException} is the custom exception class
 * created to give an exception dynamic messages and status(http) type.
 * 
 * @author Saswata Dutta
 */

public class GlobalLoanException extends RuntimeException {

	private String errorCode;
	private String errorMessage;
	
	public GlobalLoanException() {
	}
	
	public GlobalLoanException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
