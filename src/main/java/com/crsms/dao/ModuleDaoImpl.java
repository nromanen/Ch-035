package com.crsms.dao;

import java.util.ArrayList;
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
		
		String hqlDelTest = "UPDATE Test test SET test.disable=true WHERE test IN "
				+ "(SELECT testList "
				+ "FROM Module module "
				+ "JOIN module.tests testList "
				+ "WHERE module.id = :id)";
		
		String hqlDelQuestion = ""
				+ "UPDATE Question question SET question.disable=true WHERE question IN "
				+ "(SELECT questionList "
				+ "FROM Module module "
				+ "JOIN module.tests testList "
				+ "JOIN testList.questions questionList "
				+ "WHERE module.id = :id)";
		
		String hqlDelAnswer = "UPDATE Answer answer SET answer.disable=true WHERE answer IN "
				+ "(SELECT answerList "
				+ "FROM Module module "
				+ "JOIN module.tests testList "
				+ "JOIN testList.questions questionList "
				+ "JOIN questionList.answers answerList "
				+ "WHERE module.id = :id)";
		
		sessionFactory.getCurrentSession().createQuery(hqlDelTest)
			.setParameter("id", module.getId()).executeUpdate();
		sessionFactory.getCurrentSession().createQuery(hqlDelQuestion)
			.setParameter("id", module.getId()).executeUpdate();
		sessionFactory.getCurrentSession().createQuery(hqlDelAnswer)
			.setParameter("id", module.getId()).executeUpdate();
	}

}