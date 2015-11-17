package com.crsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Answer;

/**
 * @author Petro Andriets
 */

@Repository("answerDao")
public class AnswerDaoImpl extends BaseDaoImpl<Answer> implements AnswerDao {
    private static Logger logger = LogManager.getLogger(AnswerDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public AnswerDaoImpl() { }

    @SuppressWarnings("unchecked")
    @Override
    public List<Answer> getAnswersByQuestionId(Long id) {
        if (id != null) {
            logger.info("AnswerDao. Reading all answers by Question ID.");
            List<Answer> answerList = new ArrayList<Answer>();
            answerList = sessionFactory.getCurrentSession()
            						   .getNamedQuery(Answer.GET_BY_QUESTION_ID)
            						   .setParameter("id", id).list();
            logger.info("AnswerDao. Reading all answers by Question ID successfully.");
            return answerList;
        } else {
            logger.error("AnswerDao."
            		+ " Illegal argument received when answer by Question ID getting.");
            throw new IllegalArgumentException("AnswerDao."
            		+ " Illegal argument received when answer by Question ID getting.");
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
            throw new IllegalArgumentException("AnswerDao."
            		+ " Illegal argument received when answer by ID deleting.");
        }
    }
    
    @Override
	public void disable(Answer answer) {
    	answer.setDisable(false);
    	this.update(answer);
    }
    
}
