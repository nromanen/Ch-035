package com.crsms.exception;

public class ElementNotFoundException extends RuntimeException {
	
	public ElementNotFoundException() {	}
	
	public ElementNotFoundException(String message) {
		super(message);
	}

}
