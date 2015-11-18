package com.crsms.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.CourseDao;
import com.crsms.dao.QuestionDao;
import com.crsms.dao.TestDao;
import com.crsms.domain.Course;
import com.crsms.domain.Question;
import com.crsms.domain.Test;
import com.crsms.exception.ElementNotFoundException;

/**
 * @author Andriets Petro
 */

@Transactional
@Service("questionService")
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService {
    private static Logger logger = LogManager.getLogger(QuestionServiceImpl.class);

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private TestService testService;
    
    @Autowired
    CourseDao courseDao;
    
    @Autowired
    TestDao testDao;

    @Override
    public void createQuestion(Long testId, Question question) {
        logger.info("QuestionService. Creating a new question.");
        Test test = testService.getById(testId);
        test.addQuestion(question);
        questionDao.save(question);
        logger.info("QuestionService. Creating a new question successfully.");
    }
    
    @Override
    public List<Question> getQuestionsByTestId(Long testId) {
        logger.info("QuestionService. Reading all questions by Module ID.");
        return questionDao.getAllByTestId(testId);
    }

	@Override
	public void delete(Long questionId) {
		Course course = courseDao.getByQuestion(questionId);
    	if (course == null || course.getDisable()) {
			throw new ElementNotFoundException();
		}
    	
    	Test test = testDao.getByQuestion(questionId);
    	
    	if (test == null || test.getDisable()) {
			throw new ElementNotFoundException();
		}
    	
    	
    	
		Question question = questionDao.getById(questionId);
		
		if (test == null || question.getDisable()) {
			throw new ElementNotFoundException();
		}
		
		questionDao.disable(question);
		
		if (!course.getPublished()) {
			test.removeQuestion(question);
			questionDao.delete(question);
		}
	}
	
}
