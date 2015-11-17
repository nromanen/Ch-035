package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Question;

/**
 * @author Petro Andriets
 */

public interface QuestionDao extends BaseDao<Question> {

    void deleteQuestionById(Long id);

    void disable(Question question);
    
    List<Question> getAllByTestId(Long id);

}
