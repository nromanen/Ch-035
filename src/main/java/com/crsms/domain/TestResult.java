package com.crsms.domain;

public class TestResult {
	private Test test;
	private Boolean complete = false;
	//TODO field Result. How to calculate.
	private User user;
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
