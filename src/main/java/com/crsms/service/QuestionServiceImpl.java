package com.crsms.service;

import java.util.LinkedHashSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.CourseDao;
import com.crsms.dao.QuestionDao;
import com.crsms.dao.TestDao;
import com.crsms.domain.Answer;
import com.crsms.domain.Course;
import com.crsms.domain.Question;
import com.crsms.domain.Test;
import com.crsms.dto.AnswerFormDto;
import com.crsms.dto.QuestionFormDto;
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
    private CourseDao courseDao;
    
    @Autowired
    private TestDao testDao;

    @Override
    public void createQuestion(Long testId, QuestionFormDto dto) {
        logger.info("QuestionService. Creating a new question.");
        Question question = new Question();
        question.setText(dto.getText());
        Test test = testService.getById(testId);
        test.addQuestion(question);
        questionDao.save(question);
        logger.info("QuestionService. Creating a new question successfully.");
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Question createQuestionFromForm(Long testId, QuestionFormDto dto) {
        logger.info("QuestionService. Creating a new question from form.");
        Question question = new Question();
        question.setText(dto.getText());
        question.setAnswers(new LinkedHashSet());
        for (AnswerFormDto answerDto: dto.getAnswers()) {
        	Answer answer = new Answer();
        	answer.setText(answerDto.getText());
        	answer.setCorrect(answerDto.getCorrect());
        	question.getAnswers().add(answer);
        }
        Test test = testService.getTestById(testId);
        test.addQuestion(question);
        questionDao.save(question);
        logger.info("QuestionService. Creating a new question from form successfully.");
        return question;
    }
    
    @Override
    public List<Question> getQuestionsByTestId(Long testId) {
        logger.info("QuestionService. Reading all questions by Module ID.");
        return questionDao.getAllByTestId(testId);
    }


    @Override
	public void disable(Long id) {
		Question question = questionDao.getById(id);
		this.disable(question);	
	}
	
	@Override
	public void disable(Question question) {
		questionDao.disable(question);	
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
