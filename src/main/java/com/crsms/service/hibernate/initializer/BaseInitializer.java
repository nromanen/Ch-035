package com.crsms.service.hibernate.initializer;

import org.hibernate.Hibernate;

import com.crsms.util.Command;

public class BaseInitializer<E> implements Command {
	
	private E entity;
	
	public BaseInitializer(E entity) {
		this.entity = entity;
	}
	
	public void execute() {
		Hibernate.initialize(entity);
	}

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}
}
