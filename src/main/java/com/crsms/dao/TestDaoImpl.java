package com.crsms.dao;

import java.util.Set;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Test;

/**
 * 
 * @author Valerii Motresku
 *
 */


@Repository("testDao")
public class TestDaoImpl implements TestDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger log = LogManager.getLogger(TestDaoImpl.class);
	
	@Override
	public void saveTest(Test test) {
		
		try {
			sessionFactory.getCurrentSession().save(test);
		} catch (Exception e) {
			log.error("Error saveTest: " + e);
		}
	}

	@Override
	public Set<Test> getAllTest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Test getTestById(Long id) {
		Test test = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			test = (Test) session.get(Test.class, id);
		} catch (Exception e) {
			log.error("Error getTest: " + e);
		}
		//lazy initialization for FetchType.LAZY
		Hibernate.initialize(test.getQuestions());
		session.clear();
		return test;
	}

	@Override
	public void updateTest(Test test) {
		// TODO Auto-generated method stub

	}

	@Override
	public Test getTest(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
