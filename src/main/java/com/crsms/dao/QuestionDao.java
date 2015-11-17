package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Question;

/**
 * @author Petro Andriets
 */

public interface QuestionDao extends BaseDao<Question>{

    public void deleteQuestionById(Long id);

    public void disable(Question question);
    
    public List<Question> getAllByTestId(Long id);

}
