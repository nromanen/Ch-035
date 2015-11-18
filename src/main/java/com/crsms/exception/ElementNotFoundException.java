package com.crsms.exception;

public class ElementNotFoundException extends RuntimeException {
	
  private static final long serialVersionUID = 1L;

  public ElementNotFoundException() {
		super();
	}
	
	public ElementNotFoundException(String message) {
		super(message);
	}

}
