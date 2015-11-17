package com.crsms.service;

import java.util.List;

import com.crsms.domain.Question;

/**
 * @author Andriets Petro
 */

public interface QuestionService extends BaseService<Question> {

	void createQuestion(Long testId, Question question);
	
	List<Question> getQuestionsByTestId(Long testId);
    
    void disable(Long id);
    
    void disable(Question question);


}
