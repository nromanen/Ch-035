package com.crsms.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
			sessionFactory.getCurrentSession().persist(module);
		} catch (Exception e) {
			log.error("Error in save module: " + e);
		}
	}
	
	@Override
	public void update(Module module) {
		try {
			sessionFactory.getCurrentSession().update(module);
		} catch (Exception e) {
			log.error("Error in update module: " + e);
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
		Module module = null;
		try {
			module = (Module) sessionFactory.getCurrentSession().get(Module.class, id);
		} catch (Exception e) {
			log.error("Error in get module by id: " + e);
		}
		return module;
	}

	@Override
	public List<Module> getAll() {
		List<Module> list = null;
		try {
			list = sessionFactory.getCurrentSession().createCriteria(Module.class).list();
		} catch (Exception e) {
			log.error("Error in get all modules: " + e);
		}
		return list;
	}

}
