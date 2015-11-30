package com.crsms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.QuestionDao;
import com.crsms.dao.UserAnswerDao;
import com.crsms.domain.Question;
import com.crsms.domain.UserAnswer;
import com.crsms.dto.UserAnswerFormDto;

@Service("userAnswerServiceImpl")
@Transactional
public class UserAnswerServiceImpl extends BaseServiceImpl<UserAnswer> implements UserAnswerService {
	
	@Autowired
	private UserAnswerDao userAnswerDao;
	
	@Autowired
    private QuestionDao questionDao;
	
	@Override
	public UserAnswerFormDto getUserAnswerFormDto(Long testResultId, Long questionId) {
		UserAnswerFormDto userAnswerFormDto = new UserAnswerFormDto();
		userAnswerFormDto.setQuestionId(questionId);
		userAnswerFormDto.setTestResultId(testResultId);
		
		List<Long> answerIds = userAnswerDao.getAnswerIds(testResultId, questionId);
		userAnswerFormDto.setAnswerIds(answerIds);
		
		return userAnswerFormDto;
	}

	@Override
	public List<Boolean> getIsAnsweredQuestions(Long testId, Long testResultId, Long questionCount) {
		ArrayList<Boolean> isAnsweredQuestionList = new ArrayList<Boolean>((int) (long) questionCount);
		Boolean curenElement;
		for(int i = 0; i < questionCount; i++) {
			Question question = questionDao.getByTestByIndex(testId, i);
			curenElement = userAnswerDao.hasAnswereForQuestion(testResultId, question.getId());
			isAnsweredQuestionList.add(i, curenElement);
		}
		return isAnsweredQuestionList;
	}

}
