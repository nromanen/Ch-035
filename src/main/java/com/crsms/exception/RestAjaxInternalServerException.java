package com.crsms.exception;

public class RestAjaxInternalServerException extends Exception {
	
	private static final long serialVersionUID = -5574652053660447919L;
	
	public RestAjaxInternalServerException() { }
	
	public RestAjaxInternalServerException(String message) {
		super(message);
	}
	
	public RestAjaxInternalServerException(Throwable cause) {
		super(cause);
	}
	
	public RestAjaxInternalServerException(String message, Throwable cause) {
		super(message, cause);
	}
}
