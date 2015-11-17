package com.crsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crsms.dao.BaseDao;
import com.crsms.util.Command;

public class BaseServiceImpl<E> implements BaseService<E> {
	
	@Autowired
    private BaseDao<E> baseDao;

	@Override
	public void save(E entity) {
		baseDao.save(entity);
	}

	@Override
	public List<E> getAll() {
		return baseDao.getAll();
	}

	@Override
	public E getById(Long id) {
		return baseDao.getById(id);
	}

	@Override
	public E getById(Long id, Command ... initializers) {
		E entity = this.getById(id);
		for (Command initalizer : initializers) {
			initalizer.execute();
		}
		return entity;
	}

	@Override
	public void update(E entity) {
		baseDao.update(entity);
	}

	@Override
	public void delete(E entity) {
		baseDao.delete(entity);
	}
	
	
}
