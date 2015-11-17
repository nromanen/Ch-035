package com.crsms.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.AnswerDao;
import com.crsms.domain.Answer;
import com.crsms.domain.Question;

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
	public void disable(Long id) {
		Answer answer = answerDao.getById(id);
		this.disable(answer);
		
	}

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    @Override
	public void disable(Answer answer) {
		answerDao.disable(answer);
		
	}
    
}
