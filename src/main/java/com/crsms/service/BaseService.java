package com.crsms.service;

import com.crsms.util.Invocable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BaseService<E> {

	void save(E entity);
	
	List<E> getAll();

	E getById(Long id);
	
	E getById(Long id, List<Invocable<E>> initializers);

	void update(E entity);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void delete(E entity);
	
}
