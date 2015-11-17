package com.crsms.service.hibernate.initializer;

import org.hibernate.Hibernate;

import com.crsms.util.Invocable;

public class BaseInitializer<E> implements Invocable<E> {
	
	public void invoke(E entity) {
		Hibernate.initialize(entity);
	}

}
