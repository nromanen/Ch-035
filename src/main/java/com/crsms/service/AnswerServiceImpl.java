package com.crsms.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.AnswerDao;
import com.crsms.dao.CourseDao;
import com.crsms.dao.QuestionDao;
import com.crsms.domain.Answer;
import com.crsms.domain.Course;
import com.crsms.domain.Question;
import com.crsms.exception.ElementNotFoundException;

/**
 * @author Andriets Petro
 */

@Transactional
@Service("answerService")
public class AnswerServiceImpl extends BaseServiceImpl<Answer> implements AnswerService {
    private static Logger logger = LogManager.getLogger(AnswerServiceImpl.class);

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private QuestionService questionService;
    
    @Autowired
    CourseDao courseDao;
    
    @Autowired
    private QuestionDao questionDao;

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    @Override
    public void createAnswer(Long questionId, Answer answer) {
        logger.info("AnswerService. Creating a new answer.");
        Question question = questionService.getById(questionId);
        question.addAnswer(answer);
        answerDao.save(answer);
        logger.info("AnswerService. Creating a new answer successfully.");
    }

    @Override
    public List<Answer> getAnswersByQuestionId(Long questionId) {
        logger.info("AnswerService. Reading all answers by Question ID.");
        return answerDao.getAnswersByQuestionId(questionId);
    }


    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    @Override
	public void delete(Long answerId) {
    	Course course = courseDao.getByAnswer(answerId);
    	
    	if (course == null || course.getDisable()) {
    		throw new ElementNotFoundException();
    	}
    	
    	Answer answer = answerDao.getById(answerId);
    	answer.disable();
    	if (!course.getPublished()) {
    		Question question = questionDao.getByAnswer(answer.getId());
    		question.removeAnswer(answer);
    		answerDao.delete(answer);
    	}
    }
    
}
