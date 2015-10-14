package com.crsms.domain;

import java.util.Set;

public class UserInfo {
	
	Long id;
	String firstName;
	String secondName;
	String email;
	Set<Group> groups;
	
	public UserInfo() {
		super();
	}

}
