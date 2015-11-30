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
    
    /**
     * @param index a row number, numbered from <tt>0</tt>
     */
    Question getByTestByIndex(Long testId, Integer index);

	void delete(Long questionId);

	Long getCountQestionsByTest(Long testId);

}
