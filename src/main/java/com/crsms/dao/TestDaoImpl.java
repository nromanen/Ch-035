package com.crsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Test;


/**
 * @author Petro Andriets
 */

@Repository
public class TestDaoImpl extends BaseDaoImpl<Test> implements TestDao {
    private static Logger logger = LogManager.getLogger(TestDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;
  
	@SuppressWarnings("unchecked")
	@Override
	public List<Test> getAllByModuleId(Long id) {
		if (id != null) {
            logger.info("TestDao. Reading all tests by Module ID.");
            List<Test> testList = new ArrayList<Test>();
            testList = sessionFactory.getCurrentSession()
            						 .getNamedQuery(Test.GET_BY_MODULE_ID)
            						 .setParameter("id", id).list();
            logger.info("TestDao. Reading all tests by Module ID successfully.");
            return testList;
		} else {
			logger.error("TestDao. Illegal argument received when test by Module ID getting.");
			throw new IllegalArgumentException("TestDao."
					+ " Illegal argument received when test by Module ID getting.");
		}
	}
	
	@Override
    public Test getTestById(Long id) {
    	logger.info("TestDao. Reading test by ID: " + id + ".");
    	Test test = (Test) sessionFactory.getCurrentSession().get(Test.class, id);
        if (test != null) {
        	logger.info("TestDao. Reading test by ID: " + id + " successfully.");
        return test;
        } else {
        	logger.error("TestDao. Illegal argument received when test by ID getting.");
        	throw new IllegalArgumentException("TestDao."
        			+ "Illegal argument received when test by ID getting.");
        }
    }

    @Override
    public void deleteTestById(Long id) {
    	if (id != null) {
            logger.info("TestDao. Deleting test by ID: " + id + ".");
            Session session = sessionFactory.getCurrentSession();
            Test test = (Test) session.load(Test.class, new Long(id));
            if (test != null) {
                session.delete(test);
            }
            logger.info("TestDao. Deleting test by ID: " + id + " successfully.");
    	} else {
    		logger.error("TestDao. Illegal argument received when test by ID deleting.");
    		throw new IllegalArgumentException("TestDao."
    				+ " Illegal argument received when test by ID deleting.");
    	}
    }

	@Override
	public Test getByQuestion(Long questionId) {
		return (Test) getSessionFactory().getCurrentSession().getNamedQuery(Test.GET_BY_QUESTION)
										 .setParameter("id", questionId).uniqueResult();
	}

}
