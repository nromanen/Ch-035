package com.crsms.service;

import com.crsms.dao.QuestionDao;
import com.crsms.domain.Question;
import com.crsms.domain.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Andriets Petro
 */


@Transactional
@Service("questionService")
public class QuestionServiceImpl implements QuestionService{
    private static Logger logger = LogManager.getLogger(QuestionServiceImpl.class);

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private TestService testService;

    @Override
    public void createQuestion(Long testId, Question question) {
        logger.info("QuestionService. Creating a new question.");
        Test test = testService.getTestById(testId);
        test.addQuestion(question);
        questionDao.saveQuestion(question);
        logger.info("QuestionService. Creating a new question successfully.");
    }

    @Override
    public Question getQuestionById(Long id) {
        logger.info("QuestionService. Reading question by ID: " + id + ".");
        Question question = questionDao.getQuestionById(id);
        logger.info("QuestionService. Reading question by ID: " + id + " successfully.");
        return question;
    }

    @Override
    public List<Question> getQuestionsByTestId(Long testId) {
        logger.info("QuestionService. Reading all questions by Module ID.");
        return questionDao.getAllByTestId(testId);
    }

    @Override
    public void editQuestion(Question question) {
        logger.info("QuestionService. Editing question.");
        Question existingQuestion = questionDao.getQuestionById(question.getId());
        existingQuestion.setText(question.getText());
        existingQuestion.setAnswers(question.getAnswers());
        questionDao.updateQuestion(existingQuestion);
        logger.info("QuestionService. Editing question successfully.");
    }

//    @Override
//    public void deleteQuestionById(Long id) {
//        logger.info("QuestionService. Deleting question by ID: " + id + ".");
//        questionDao.deleteQuestionById(id);
//        logger.info("QuestionService. Deleting question by ID: " + id + " successfully.");
//    }

	@Override
	public void disable(Long id) {
		Question question = questionDao.getQuestionById(id);
		this.disable(question);
		
	}
	
	@Override
	public void disable(Question question) {
		questionDao.disable(question);
		
	}
}
