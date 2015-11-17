package com.crsms.dao;

import com.crsms.domain.Course;
import com.crsms.domain.Module;
import com.crsms.domain.Question;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements QuestionDao {
    private static Logger logger = LogManager.getLogger(QuestionDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public QuestionDaoImpl() {}

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
            logger.error("QuestionDao. Illegal argument received when questions by Test ID getting.");
            throw new IllegalArgumentException("QuestionDao. Illegal argument received when questions by Test ID getting.");
        }
    }
    
    @Override
	public void disable(Question question) {
    	question.setDisable(true);
    	this.update(question);
    	
    	String hqlDelAnswer = "UPDATE Answer answer SET answer.disable=true WHERE answer IN "
				+ "(SELECT answerList "
				+ "FROM Question question "
				+ "JOIN question.answers answerList "
				+ "WHERE question.id = :id)";
    	
    	sessionFactory.getCurrentSession().createQuery(hqlDelAnswer)
			.setParameter("id", question.getId()).executeUpdate();
    }

}
