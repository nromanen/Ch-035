package com.crsms.dao;

import java.util.ArrayList;
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
		List<Module> list = new ArrayList<Module>();
		try {
			list = sessionFactory.getCurrentSession()
								 .getNamedQuery(Module.GET_ALL_BY_COURSE_ID)
								 .setParameter("id", courseId).list();
		} catch (Exception e) {
			logger.error("Error in getting all modules by course id: " + e);
		}
		return list;
	}

	@Override
	public void deleteById(Long id) {
	    try {
	    	sessionFactory.getCurrentSession()
	    				  .getNamedQuery(Module.DELETE_BY_ID)
	    				  .setParameter("id", id).executeUpdate();
		} catch (Exception e) {
			logger.error("Error in delete module by id: " + e);
		}
	}

	@Override
	public void disable(Module module) {
		module.setDisable(true);
		this.update(module);
		
		sessionFactory.getCurrentSession().getNamedQuery(Module.DISABLE_TESTS)
			.setParameter("id", module.getId()).executeUpdate();
		sessionFactory.getCurrentSession().getNamedQuery(Module.DISABLE_QUESTIONS)
			.setParameter("id", module.getId()).executeUpdate();
		sessionFactory.getCurrentSession().getNamedQuery(Module.DISABLE_ANSWERS)
			.setParameter("id", module.getId()).executeUpdate();
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

}