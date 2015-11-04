package com.crsms.dao;

import com.crsms.domain.Answer;
import com.crsms.domain.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Petro Andriets
 */

public class AnswerDaoImpl implements AnswerDao{
    private static Logger logger = LogManager.getLogger(AnswerDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public AnswerDaoImpl() {}

    @Override
    public void saveAnswer(Answer answer) {
        if (answer != null) {
            logger.info("AnswerDao. Creating a new answer.");
            Session session = sessionFactory.getCurrentSession();
            session.persist(answer);
            logger.info("AnswerDao. Creating a new answer successfully.");
        } else {
            logger.error("AnswerDao. Illegal argument received when answer saving.");
            throw new IllegalArgumentException("AnswerDao. Illegal argument received when answer saving.");
        }
    }

    @Override
    public Answer getAnswerById(Long id) {
        logger.info("AnswerDao. Reading answer by ID: " + id + ".");
        Answer answer = (Answer) sessionFactory.getCurrentSession().get(Answer.class, id);
        if (answer != null) {
            logger.info("AnswerDao. Reading answer by ID: " + id + " successfully.");
            return answer;
        } else {
            logger.error("AnswerDao. Illegal argument received when answer by ID getting.");
            throw new IllegalArgumentException("AnswerDao. Illegal argument received when answer by ID getting.");
        }
    }

    @Override
    public List<Answer> getAnswersByQuestionId(Long id) {
        if (id != null) {
            logger.info("AnswerDao. Reading all answers by Question ID.");
            List<Answer> answerList = new ArrayList<Answer>();
            String hql = "FROM Answer WHERE question_id = :id order by id asc";
            Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id);
            answerList = query.list();
            logger.info("AnswerDao. Reading all answers by Question ID successfully.");
            return answerList;
        } else {
            logger.error("AnswerDao. Illegal argument received when answer by Question ID getting.");
            throw new IllegalArgumentException("AnswerDao. Illegal argument received when answer by Question ID getting.");
        }
    }

    @Override
    public void updateAnswer(Answer answer) {
        if (answer != null) {
            logger.info("AnswerDao. Updating answer.");
            Session session = sessionFactory.getCurrentSession();
            session.update(answer);
            logger.info("AnswerDao. Updating answer successfully.");
        } else {
            logger.error("AnswerDao. Illegal argument received when answer updating.");
            throw new IllegalArgumentException("AnswerDao. Illegal argument received when answer updating.");
        }
    }

    @Override
    public void deleteAnswerById(Long id) {
        if (id != null) {
            logger.info("AnswerDao. Deleting answer by ID: " + id + ".");
            Session session = sessionFactory.getCurrentSession();
            Answer answer = (Answer) session.load(Answer.class, new Long(id));
            if (answer != null) {
                session.delete(answer);
            }
            logger.info("AnswerDao. Deleting answer by ID: " + id + " successfully.");
        } else {
            logger.error("AnswerDao. Illegal argument received when answer by ID deleting.");
            throw new IllegalArgumentException("AnswerDao. Illegal argument received when answer by ID deleting.");
        }
    }
}
