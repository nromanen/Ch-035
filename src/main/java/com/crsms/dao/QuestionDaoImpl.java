package com.crsms.dao;

import com.crsms.domain.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Petro Andriets
 */

@Repository("questionDao")
public class QuestionDaoImpl implements QuestionDao {
    private static Logger logger = LogManager.getLogger(QuestionDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public QuestionDaoImpl() {}

    @Override
    public void saveQuestion(Question question) {
        if (question != null) {
            logger.info("QuestionDao. Creating a new question.");
            Session session = sessionFactory.getCurrentSession();
            session.persist(question);
            logger.info("QuestionDao. Creating a new question successfully.");
        } else {
            logger.error("QuestionDao. Illegal argument received when question saving.");
            throw new IllegalArgumentException("QuestionDao. Illegal argument received when question saving.");
        }
    }

    @Override
    public Question getQuestionById(Long id) {
        logger.info("QuestionDao. Reading question by ID: " + id + ".");
        Question question = (Question) sessionFactory.getCurrentSession().get(Question.class, id);
        if (question != null) {
            logger.info("QuestionDao. Reading question by ID: " + id + " successfully.");
            return question;
        } else {
            logger.error("QuestionDao. Illegal argument received when question by ID getting.");
            throw new IllegalArgumentException("QuestionDao. Illegal argument received when question by ID getting.");
        }
    }

    @Override
    public List<Question> getAllByTestId(Long id) {
        if (id != null) {
            logger.info("QuestionDao. Reading all questions by Test ID.");
            List<Question> questionList = new ArrayList<Question>();
            String hql = "FROM Question WHERE test_id = :id order by id asc";
            Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id);
            questionList = query.list();
            logger.info("QuestionDao. Reading all questions by Test ID successfully.");
            return questionList;
        } else {
            logger.error("QuestionDao. Illegal argument received when questions by Test ID getting.");
            throw new IllegalArgumentException("QuestionDao. Illegal argument received when questions by Test ID getting.");
        }
    }

    @Override
    public void updateQuestion(Question question) {
        if (question != null) {
            logger.info("QuestionDao. Updating question.");
            Session session = sessionFactory.getCurrentSession();
            session.update(question);
            logger.info("QuestionDao. Updating question successfully.");
        } else {
            logger.error("QuestionDao. Illegal argument received when question updating.");
            throw new IllegalArgumentException("QuestionDao. Illegal argument received when question updating.");
        }
    }

    @Override
    public void deleteQuestionById(Long id) {
        if (id != null) {
            logger.info("QuestionDao. Deleting question by ID: " + id + ".");
            Session session = sessionFactory.getCurrentSession();
            Question question = (Question) session.load(Question.class, new Long(id));
            if (question != null) {
                session.delete(question);
            }
            logger.info("QuestionDao. Deleting question by ID: " + id + " successfully.");
        } else {
            logger.error("QuestionDao. Illegal argument received when question by ID deleting.");
            throw new IllegalArgumentException("QuestionDao. Illegal argument received when question by ID deleting.");
        }
    }

}
