package com.crsms.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.util.Invocable;

@Transactional
public interface BaseService<E> {

	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void save(E entity);
	
	List<E> getAll();

	E getById(Long id);
	
	E getById(Long id, List<Invocable<E>> initializers);

	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void update(E entity);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void delete(E entity);
	
}
