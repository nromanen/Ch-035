package com.crsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Question;

/**
 * @author Petro Andriets
 */

@Repository
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements QuestionDao {
    private static Logger logger = LogManager.getLogger(QuestionDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

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
            throw new IllegalArgumentException("QuestionDao."
            		+ " Illegal argument received when question by ID deleting.");
        }
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Question> getAllByTestId(Long id) {
        if (id != null) {
            logger.info("QuestionDao. Reading all questions by Test ID.");
            List<Question> questionList = new ArrayList<Question>();
            questionList = sessionFactory.getCurrentSession().getNamedQuery(Question.GET_BY_TEST_ID)
                                                             .setParameter("id", id).list();
            logger.info("QuestionDao. Reading all questions by Test ID successfully.");
            return questionList;
        } else {
            logger.error("QuestionDao."
            		+ " Illegal argument received when questions by Test ID getting.");
            throw new IllegalArgumentException("QuestionDao."
            		+ " Illegal argument received when questions by Test ID getting.");
        }
    }
    
    /**
     * @param startPosition a row number, numbered from <tt>0</tt>
     */
    @SuppressWarnings("unchecked")
    @Override
	public List<Question> getAllByTest(Long testId, Integer startPosition, Integer maxResult) {
    	if (testId != null) {
            logger.info("QuestionDao. Reading all questions by Test ID.");
            List<Question> questionList = new ArrayList<Question>();
            questionList = sessionFactory.getCurrentSession().getNamedQuery(Question.GET_BY_TEST)
                                         .setParameter("id", testId)
                                         .setFirstResult(startPosition)
                                         .setMaxResults(maxResult).list();
            logger.info("QuestionDao. Reading all questions by Test ID successfully.");
            return questionList;
        } else {
            logger.error("QuestionDao."
            		+ " Illegal argument received when questions by Test ID getting.");
            throw new IllegalArgumentException("QuestionDao."
            		+ " Illegal argument received when questions by Test ID getting.");
        }
	}

	@Override
	public Question getByAnswer(Long answerId) {
		return (Question) getSessionFactory().getCurrentSession().getNamedQuery(Question.GET_BY_ANSWER)
				.setParameter("id", answerId).uniqueResult();
	}
	
	@Override
	public Long countByTest(Long testId) {
		return (Long) getSessionFactory().getCurrentSession().getNamedQuery(Question.GET_QUESTION_COUNT_BY_TEST)
				 .setParameter("id", testId).uniqueResult();
	}

	@Override
	public Question getByTestByIndex(Long testId, Integer index) {
		if (testId != null) {
            logger.info("QuestionDao. getByTestByIndex.");
            return (Question) sessionFactory.getCurrentSession().getNamedQuery(Question.GET_BY_TEST)
                                         .setParameter("id", testId)
                                         .setFirstResult(index)
                                         .setMaxResults(1).uniqueResult();
        } else {
            logger.error("QuestionDao."
            		+ " Illegal argument received when questions by Test ID getting.");
            throw new IllegalArgumentException("QuestionDao."
            		+ " Illegal argument received when questions by Test ID getting.");
        }
	}

}
