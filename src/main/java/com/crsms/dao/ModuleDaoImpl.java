package com.crsms.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Module;
import com.crsms.domain.Test;

/**
 * 
 * @author St. Roman
 *
 */

@Repository
public class ModuleDaoImpl extends BaseDaoImpl<Module> implements ModuleDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger logger = LogManager.getLogger(ModuleDaoImpl.class);
	
	public ModuleDaoImpl() {
		super(Module.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> getAllByCourseId(Long courseId) {
		try {
			return sessionFactory.getCurrentSession()
								 .getNamedQuery(Module.GET_ALL_BY_COURSE_ID)
								 .setParameter("id", courseId).list();
		} catch (Exception e) {
			logger.error("Error in getting all modules by course id: " + e);
			throw e;
		}
	}

	@Override
	public void deleteById(Long id) {
	    try {
	    	sessionFactory.getCurrentSession()
	    				  .getNamedQuery(Module.DELETE_BY_ID)
	    				  .setParameter("id", id).executeUpdate();
		} catch (Exception e) {
			logger.error("Error in delete module by id: " + e);
			throw e;
		}
	}
	
	@Override
	public Module getByTest(Test test) {
		return (Module) sessionFactory.getCurrentSession().getNamedQuery(Module.GET_BY_TEST)
				.setParameter("id", test.getId()).uniqueResult();
	}

	@Override
	public Module getByTest(Long testId) {
		return (Module) sessionFactory.getCurrentSession().getNamedQuery(Module.GET_BY_TEST)
				.setParameter("id", testId).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> getAllAssociatedWithResource(Long resourceId) {
		return (List<Module>) sessionFactory.getCurrentSession().getNamedQuery(Module.GET_ALL_ASSOCIATED_WITH_RESOURCE)
				.setParameter("resource_id", resourceId).list();
	}

}