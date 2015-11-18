package com.crsms.service;

import java.util.LinkedHashSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.QuestionDao;
import com.crsms.domain.Answer;
import com.crsms.domain.Question;
import com.crsms.domain.Test;
import com.crsms.dto.AnswerFormDto;
import com.crsms.dto.QuestionFormDto;

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

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    @Override
	public void disable(Long id) {
		Question question = questionDao.getById(id);
		this.disable(question);	
	}
	
	@Override
	public void disable(Question question) {
		questionDao.disable(question);	
	}
	
}
