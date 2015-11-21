package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Question;

/**
 * @author Petro Andriets
 */

public interface QuestionDao extends BaseDao<Question> {

    void deleteQuestionById(Long id);
    
    List<Question> getAllByTestId(Long id);
    
    Question getByAnswer(Long answerId);

}
