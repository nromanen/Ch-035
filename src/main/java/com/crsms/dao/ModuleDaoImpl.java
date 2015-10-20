package com.crsms.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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
	
	private static Logger log = LogManager.getLogger(ModuleDaoImpl.class);

	@Override
	public void save(Module module) {
		try {
			sessionFactory.getCurrentSession().save(module);
		} catch (Exception e) {
			log.error("Error in save module: " + e);
		}
	}

	@Override
	public void delete(Module module) {
		try {
			sessionFactory.getCurrentSession().delete(module);
		} catch (Exception e) {
			log.error("Error in delete module: " + e);
		}
	}

	@Override
	public Module getById(Long id) {
		Query query = sessionFactory.getCurrentSession()
				.getNamedQuery("getModuleById").setLong("id", id);
		Module module = null;
		try {
			module = (Module) query.list();
		} catch (Exception e) {
			log.error("Error in get module by id: " + e);
		}
		return module;
	}

}
