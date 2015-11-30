package com.crsms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.AnswerDao;
import com.crsms.dao.QuestionDao;
import com.crsms.dao.TestDao;
import com.crsms.dao.TestResultDao;
import com.crsms.dao.UserAnswerDao;
import com.crsms.domain.Answer;
import com.crsms.domain.Question;
import com.crsms.domain.Test;
import com.crsms.domain.TestResult;
import com.crsms.domain.User;
import com.crsms.domain.UserAnswer;
import com.crsms.dto.UserAnswerAndQuestionDto;
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
	private TestDao testDao;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
    private QuestionDao questionDao;
	
	@Autowired
	private UserAnswerService userAnswerService;
	
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
	public TestResult getById(Long testResultId, String email) {
		User user = userService.getUserByEmail(email);
		return testResultDao.getByIdAndUser(testResultId, user.getId());
	}

	@Override
	public void save(UserAnswerFormDto userAnswerFormDto) {
		//TODO: check TestResult is open
		TestResult testResult = testResultDao.getById(userAnswerFormDto.getTestResultId());
		Question question = questionService.getById(userAnswerFormDto.getQuestionId());
		//TODO replace on something better
		userAnswerDao.deleteByTestResultAndQuestion(userAnswerFormDto.getTestResultId(), userAnswerFormDto.getQuestionId());
		
		
		
		//TODO save new useranswer
		UserAnswer userAnswer;
		Answer answer;
		if(userAnswerFormDto.getAnswerIds() == null) return;
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

	@Override
	public void complete(Long testResultId) {
		testResultDao.getById(testResultId).setComplete(true);
	}

	@Override
	public Double getScore(Long testResultId) {
		TestResult testResult = testResultDao.getById(testResultId);
		Long allCorrectAnswer = testDao.countCorrectAnswer(testResult.getTest().getId());
		Long userCorectAnswer = testResultDao.counCorrectAnswers(testResultId);
		Long userIncorectAnswer = testResultDao.counIncorrectAnswers(testResultId);
		double score = (userCorectAnswer - userIncorectAnswer) * 100.0 / (allCorrectAnswer);
		if(score < 0) score = 0;
		return score;
	}

	@Override
	public List<UserAnswerAndQuestionDto> getUserAnswerAndQuestionList(
			Long testResultId
	) {
		TestResult testResult = testResultDao.getById(testResultId);
		long testId = testResult.getTest().getId();
		long questionCount = questionService.getCountQestionsByTest(testId);
		List<UserAnswerAndQuestionDto> userAnswerAndQuestionList = new ArrayList<UserAnswerAndQuestionDto>((int) questionCount);
		
		UserAnswerAndQuestionDto curenElement;
		for(int i = 0; i < questionCount; i++) {
			Question question = questionDao.getByTestByIndex(testId, i);
			UserAnswerFormDto userAnswerForm = userAnswerService.getUserAnswerFormDto(testResultId, question.getId());
			
			curenElement = new UserAnswerAndQuestionDto();
			curenElement.setQuestion(question);
			curenElement.setUserAnswerForm(userAnswerForm);
			userAnswerAndQuestionList.add(curenElement);
		}
		return userAnswerAndQuestionList;
	}

}
