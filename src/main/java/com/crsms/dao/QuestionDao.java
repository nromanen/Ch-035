package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Question;

/**
 * @author Petro Andriets
 */

public interface QuestionDao extends BaseDao<Question> {

    void deleteQuestionById(Long id);
    
    List<Question> getAllByTestId(Long id);
    
    /**
     * @param startPosition a row number, numbered from <tt>0</tt>
     */
    List<Question> getAllByTest(Long testId, Integer startPosition, Integer maxResult);
    
    /**
     * @param index a row number, numbered from <tt>0</tt>
     */
    Question getByTestByIndex(Long testId, Integer index);
    
    Question getByAnswer(Long answerId);

	Long countByTest(Long testId);

}
