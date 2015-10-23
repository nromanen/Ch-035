package com.crsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Module;

/**
 * 
 * @author St. Roman
 *
 */

@Repository("moduleDao")
public class ModuleDaoImpl implements ModuleDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger logger = LogManager.getLogger(ModuleDaoImpl.class);

	@Override
	public void save(Module module) {
		try {
			sessionFactory.getCurrentSession().persist(module);
		} catch (Exception e) {
			logger.error("Error in save module: " + e);
		}
	}
	
	@Override
	public void update(Module module) {
		try {
			sessionFactory.getCurrentSession().update(module);
		} catch (Exception e) {
			logger.error("Error in update module: " + e);
		}
	}

	@Override
	public void delete(Module module) {
		try {
			sessionFactory.getCurrentSession().delete(module);
		} catch (Exception e) {
			logger.error("Error in delete module: " + e);
		}
	}

	@Override
	public Module getById(Long id) {
		Module module = null;
		try {
			module = (Module) sessionFactory.getCurrentSession().get(Module.class, id);
		} catch (Exception e) {
			logger.error("Error in get module by id: " + e);
		}
		//Hibernate.initialize(module.getCourse());
		return module;
	}

	@Override
	public List<Module> getAll() {
		List<Module> list = new ArrayList<Module>();
		try {
			String hql = "from Module m order by m.id asc";
			list = sessionFactory.getCurrentSession().createQuery(hql).list();
		} catch (Exception e) {
			logger.error("Error in get all modules: " + e);
		}
		return list;
	}

	@Override
	public void deleteById(Long id) {
		String hql = "delete Module where id = :id";
	    Query qeury = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id);
	    try {
	    	qeury.executeUpdate();
		} catch (Exception e) {
			logger.error("Error in delete module by id: " + e);
		}
	}

}
