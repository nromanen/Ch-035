package com.crsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.UserAnswerDao;
import com.crsms.domain.UserAnswer;
import com.crsms.dto.UserAnswerFormDto;

@Service("userAnswerServiceImpl")
@Transactional
public class UserAnswerServiceImpl extends BaseServiceImpl<UserAnswer> implements UserAnswerService {
	
	@Autowired
	private UserAnswerDao userAnswerDao;
	
	@Override
	public UserAnswerFormDto getUserAnswerFormDto(Long testResultId, Long questionId) {
		UserAnswerFormDto userAnswerFormDto = new UserAnswerFormDto();
		userAnswerFormDto.setQuestionId(questionId);
		userAnswerFormDto.setTestResultId(testResultId);
		
		List<Long> answerIds = userAnswerDao.getAnswerIds(testResultId, questionId);
		userAnswerFormDto.setAnswerIds(answerIds);
		
		return userAnswerFormDto;
	}

}
