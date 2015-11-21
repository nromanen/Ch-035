package com.crsms.service;

import java.util.List;

import com.crsms.domain.Question;
import com.crsms.dto.QuestionFormDto;

/**
 * @author Andriets Petro
 */

public interface QuestionService extends BaseService<Question> {

	void createQuestion(Long testId, QuestionFormDto dto);
	
	List<Question> getQuestionsByTestId(Long testId);
    
    Question createQuestionFromForm(Long testId, QuestionFormDto dto);

	void delete(Long questionId);

}
