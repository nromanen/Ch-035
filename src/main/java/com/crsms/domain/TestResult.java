package com.crsms.domain;

public class TestResult {
	private Test test;
	private Boolean complete = false;
	//TODO field Result. Hoe to calculate.
	//TODO commit for private User user;
	
	public TestResult() {}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

}
