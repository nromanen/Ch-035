package com.crsms.dao;

import java.util.List;

/**
 * 
 * @author Valerii Motresku
 *
 */

public interface BaseDao<E> {
	
	void save(E entity);
	
	List<E> getAll();

	E getById(Long id);

	void update(E entity);
	
	void delete(E entity);
}
