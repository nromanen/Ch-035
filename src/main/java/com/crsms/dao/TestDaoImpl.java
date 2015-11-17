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

@Repository("testDao")
public class TestDaoImpl extends BaseDaoImpl<Test> implements TestDao {
    private static Logger logger = LogManager.getLogger(TestDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public TestDaoImpl() { }
  
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
	public void disableTestById(Long testId) {
		if (testId != null) {
            logger.info("TestDao. disabling test.");
            Test test = (Test) sessionFactory.getCurrentSession()
            								 .load(Test.class, new Long(testId));
            this.disable(test);
            logger.info("TestDao. disabling test successfully.");
    	} else {
    		logger.error("TestDao. Illegal argument received when test deleting.");
    		throw new IllegalArgumentException("TestDao."
    				+ " Illegal argument received when test disabling.");
    	}
		
	}
	
	@Override
	public void disable(Test test) {
		test.setDisable(true);
		this.update(test);
		
		String hqlDelQuestion = ""
				+ "UPDATE Question question SET question.disable=true WHERE question IN "
				+ "(SELECT questionList "
				+ "FROM Test test "
				+ "JOIN test.questions questionList "
				+ "WHERE test.id = :id)";
		
		String hqlDelAnswer = ""
				+ "UPDATE Answer answer SET answer.disable=true WHERE answer IN "
				+ "(SELECT answerList "
				+ "FROM Test test "
				+ "JOIN test.questions questionList "
				+ "JOIN questionList.answers answerList "
				+ "WHERE test.id = :id)";
		
		sessionFactory.getCurrentSession().createQuery(hqlDelQuestion)
			.setParameter("id", test.getId()).executeUpdate();
		sessionFactory.getCurrentSession().createQuery(hqlDelAnswer)
			.setParameter("id", test.getId()).executeUpdate();
	}

}
