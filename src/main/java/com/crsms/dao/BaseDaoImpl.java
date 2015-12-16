package com.crsms.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * Generic DAO implementation
 * @author Valerii Motresku
 *
 */

public class BaseDaoImpl<E> implements BaseDao<E> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private final Logger logger = LogManager.getLogger(BaseDaoImpl.class);
	
	private final Class<E> entityClass;
	
	@SuppressWarnings(value = "unchecked")
	public BaseDaoImpl() {
		this.entityClass = (Class<E>) ((ParameterizedType) getClass()
	            .getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public BaseDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }
	
	public Class<E> getEntityClass() {
		return entityClass;
	}

	@Override
	public void save(E entity) {
		try {
			sessionFactory.getCurrentSession().persist(entity);
			logger.info("DAO: save: " + this.entityClass.getSimpleName());
		} catch (HibernateException e) {
			logger.error("Error save: " + this.entityClass.getSimpleName() + " : " + e);
			throw e;
		}
	}
	
	@Override
	public void delete(E entity) {
		try {
			sessionFactory.getCurrentSession().delete(entity);
		} catch (Exception e) {
			logger.error("Error in delete: " + this.entityClass.getSimpleName() + " : " + e);
			throw e;
		}
	}

	@Override
	@SuppressWarnings(value = "unchecked")
	public List<E> getAll() {
		String hql = "FROM " + this.entityClass.getSimpleName() + " ORDER BY id";
		try {
			return (List<E>) sessionFactory.getCurrentSession().createQuery(hql).list();
		} catch (Exception e) {
			logger.error("Error in getAll(): " + this.entityClass.getSimpleName() + " : " + e);
			throw e;
		}
	}

	
	@Override
	@SuppressWarnings(value = "unchecked")
	public E getById(Long id) {
		try {
			return (E) sessionFactory.getCurrentSession().get(this.entityClass, id);
		} catch (Exception e) {
			logger.error("Error in get " + this.entityClass.getSimpleName() + " by id: " + e);
			throw e;
		}
	}

	@Override
	public void update(E entity) {
		try {
			sessionFactory.getCurrentSession().update(entity);
		} catch (Exception e) {
			logger.error("Error update: " + this.entityClass.getSimpleName() + " : " + e);
			throw e;
		}
	}

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Logger getLogger() {
		return logger;
	}

}
