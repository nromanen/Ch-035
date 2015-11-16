package com.crsms.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BaseService<E> {

	void save(E entity);
	
	List<E> getAll();

	E getById(Long id);

	void update(E entity);
	
	void delete(E entity);
	
}
