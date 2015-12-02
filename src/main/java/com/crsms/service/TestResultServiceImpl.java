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
import com.crsms.exception.ElementNotFoundException;

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
		TestResult testResult = testResultDao.getByIdAndUser(testResultId, user.getId());
		if (testResult == null) {
			throw new ElementNotFoundException();
		}
		return testResult;
	}

	@Override
	public void save(UserAnswerFormDto userAnswerFormDto, String email) {
		User user = userService.getUserByEmail(email);
		TestResult testResult = testResultDao.getByIdAndUser(userAnswerFormDto.getTestResultId(), user.getId());
		if(testResult.getComplete())
			return;
		
		Question question = questionService.getById(userAnswerFormDto.getQuestionId());
		//TODO replace on something better
		userAnswerDao.deleteByTestResultAndQuestion(userAnswerFormDto.getTestResultId(), userAnswerFormDto.getQuestionId());
		
		int countAnswer = 0;
		for(Answer answer: question.getAnswers()) 
			if(!answer.getDisable())  countAnswer++;
		
		if(countAnswer == 0) return;
		
		UserAnswer userAnswer;
		Boolean checked;
		Boolean correctAnswer;
		Double cost = 1.0 / countAnswer;
		for(Answer answer: question.getAnswers()) {
			if(answer.getDisable()) continue;
			userAnswer = new UserAnswer();
			userAnswer.setTestResult(testResult);
			userAnswer.setQuestion(question);
			userAnswer.setAnswer(answer);
			
			if(userAnswerFormDto.getCheckedAnswerIds() == null) 
				checked = false;
			else
				checked = userAnswerFormDto.getCheckedAnswerIds().contains( answer.getId() );
			correctAnswer = checked == answer.getCorrect();
			
			userAnswer.setChecked(checked);
			userAnswer.setCorrectAnswer(correctAnswer);
			userAnswer.setCost(cost);
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
		Test test = testResult.getTest();
		
		double score = 0;
		double maxScore = 0; 
		for(Question question : test.getQuestions() ) {
			if(question.getDisable()) continue;
			maxScore++;
			
			score += getScoreForQuestion(testResultId, question.getId());
		}
		
		if(maxScore == 0)
			return 0.;
		
		return score / maxScore * 100;
	}

	private double getScoreForQuestion(Long testResultId, Long questionId) {
		List<UserAnswer> userAnswerList = userAnswerDao.getAnswers(testResultId, questionId);
		boolean allChecked = true;
		boolean allUnchecked = true;
		double score = 0;
		for(UserAnswer userAnswer : userAnswerList) {
			if(userAnswer.getChecked())
				allUnchecked = false;
			else
				allChecked = false;
			
			if(userAnswer.getCorrectAnswer())
				score += userAnswer.getCost();
		}
		
		if(allUnchecked || allChecked)
			return 0;
		
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
