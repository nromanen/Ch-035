package com.crsms.exception;

public class ElementNotFoundException extends RuntimeException {
	
  private static final long serialVersionUID = 1L;

	public ElementNotFoundException() {	}
	
	public ElementNotFoundException(String message) {
		super(message);
	}

}
