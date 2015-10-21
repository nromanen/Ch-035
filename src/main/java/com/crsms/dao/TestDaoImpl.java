package com.crsms.dao;

import com.crsms.domain.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Petro Andriets
 */

@Repository("testDao")
public class TestDaoImpl implements TestDao {
    @Autowired
    private SessionFactory sessionFactory;

    public TestDaoImpl() {
    }

    @Override
    public void saveTest(Test test) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(test);
    }

    @Override
    public Test getTestById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Test test = (Test) session.load(Test.class, new Long(id));
        return test;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Test> getAllTests() {
        Session session = sessionFactory.getCurrentSession();
        Set<Test> testSet = new LinkedHashSet<Test>(session.createQuery("from Test").list());
        return testSet;
    }

    @Override
    public void updateTest(Test test) {
        Session session = sessionFactory.getCurrentSession();
        session.update(test);
    }

    @Override
    public void deleteTest(Test test) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(test);
    }

    @Override
    public void deleteTestById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Test test = (Test) session.load(Test.class, new Long(id));
        if (test != null) {
            session.delete(test);
        }
    }

}
