package com.crsms.domain;

import java.util.Set;

public class Group {
	private Long id;
	private String name;
	private Type type;
	private Set<User> users;
	private Set<Course> courses;
	private Long maxUserCount;
	private Boolean recruited;
	
	public Group() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Long getMaxUserCount() {
		return maxUserCount;
	}

	public void setMaxUserCount(Long maxUserCount) {
		this.maxUserCount = maxUserCount;
	}

	public Boolean getRecruited() {
		return recruited;
	}

	public void setRecruited(Boolean recruited) {
		this.recruited = recruited;
	}
	
	
}
