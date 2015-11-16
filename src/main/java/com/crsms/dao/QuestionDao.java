package com.crsms.dao;

import com.crsms.domain.Question;

import java.util.List;

/**
 * @author Petro Andriets
 */

public interface QuestionDao {

    public void saveQuestion(Question question);

    public Question getQuestionById(Long id);

    public List<Question> getAllByTestId(Long id);

    public void updateQuestion(Question question);

    public void deleteQuestionById(Long id);

    public void disable(Question question);

}
