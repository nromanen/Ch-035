package com.crsms.service;

import com.crsms.domain.Question;

import java.util.List;

/**
 * @author Andriets Petro
 */

public interface QuestionService extends BaseService<Question> {

	public void createQuestion(Long testId, Question question);
	
	public List<Question> getQuestionsByTestId(Long testId);
    
    public void disable(Long id);
    
    public void disable(Question question);


}
