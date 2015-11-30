package com.crsms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.AnswerDao;
import com.crsms.dao.TestResultDao;
import com.crsms.dao.UserAnswerDao;
import com.crsms.domain.Answer;
import com.crsms.domain.Question;
import com.crsms.domain.Test;
import com.crsms.domain.TestResult;
import com.crsms.domain.User;
import com.crsms.domain.UserAnswer;
import com.crsms.dto.UserAnswerFormDto;

@Service("testResultService")
@Transactional
public class TestResultServiceImpl implements TestResultService {
	
	@Autowired
	private TestResultDao testResultDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private UserAnswerDao userAnswerDao;
	
	@Autowired
	private AnswerDao answerDao;

	@Override
	public TestResult getCurrent(Long testId, String email) {
		User user = userService.getUserByEmail(email);
		
		TestResult testResult = testResultDao.getCurrent(testId, user.getId());
		
		if(testResult == null){
			Test test = testService.getById(testId);
			
			testResult = new TestResult();
			testResult.setComplete(false);
			testResult.setUser(user);
			testResult.setTest(test);
			
			testResultDao.save(testResult);
		}
		
		
		return testResult;
	}

	@Override
	public void save(UserAnswerFormDto userAnswerFormDto) {
		TestResult testResult = testResultDao.getById(userAnswerFormDto.getTestResultId());
		Question question = questionService.getById(userAnswerFormDto.getQuestionId());
		//TODO replace on something better
		userAnswerDao.deleteByTestResultAndQuestion(userAnswerFormDto.getTestResultId(), userAnswerFormDto.getQuestionId());
		
		
		
		//TODO save new useranswer
		UserAnswer userAnswer;
		Answer answer;
		//if(userAnswerFormDto.getAnswerIds() == null) return;
		for(Long userAnswerId : userAnswerFormDto.getAnswerIds()){
			userAnswer = new UserAnswer();
			userAnswer.setTestResult(testResult);
			userAnswer.setQuestion(question);
			//TODO: check answer
			answer = answerDao.getById(userAnswerId);
			userAnswer.setAnswer(answer);
			userAnswerDao.save(userAnswer);
		}
		
	}


}
