package com.crsms.dao;

import com.crsms.domain.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Petro Andriets
 */

@Repository("testDao")
public class TestDaoImpl implements TestDao {
    private static Logger logger = LogManager.getLogger(TestDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public TestDaoImpl() {
    }

    @Override
    public void saveTest(Test test) {
        logger.info("TestDao. Creating a new test.");
        Session session = sessionFactory.getCurrentSession();
        session.persist(test);
        logger.info("TestDao. Creating a new test successfully.");
    }

    @Override
    public Test getTestById(Long id) {
        logger.info("TestDao. Reading test by ID: " + id + ".");
        Session session = sessionFactory.getCurrentSession();
        Test test = (Test) session.load(Test.class, new Long(id));
        Hibernate.initialize(test.getModule());
        logger.info("TestDao. Reading test by ID: " + id + " successfully.");
        return test;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Test> getAllTests() {
        logger.info("TestDao. Reading all tests.");
        List<Test> testList = new ArrayList<Test>();
        String hql = "from Test";
        Session session = sessionFactory.getCurrentSession();
        testList = new ArrayList<Test>(session.createQuery(hql).list());
        logger.info("TestDao. Reading all tests successfully.");
        return testList;
    }

    @Override
    public void updateTest(Test test) {
        logger.info("TestDao. Updating test.");
        Session session = sessionFactory.getCurrentSession();
        session.update(test);
        logger.info("TestDao. Updating test successfully.");
    }

    @Override
    public void deleteTest(Test test) {
        logger.info("TestDao. Deleting test.");
        Session session = sessionFactory.getCurrentSession();
        session.delete(test);
        logger.info("TestDao. Deleting test successfully.");
    }

    @Override
    public void deleteTestById(Long id) {
        logger.info("TestDao. Deleting test by ID: " + id + ".");
        Session session = sessionFactory.getCurrentSession();
        Test test = (Test) session.load(Test.class, new Long(id));
        if (test != null) {
            session.delete(test);
        }
        logger.info("TestDao. Deleting test by ID: " + id + " successfully.");
    }

}
