package com.crsms.service;

import com.crsms.domain.Question;

import java.util.List;

/**
 * @author Andriets Petro
 */

public interface QuestionService {

    public void createQuestion(Long testId, Question question);

    public Question getQuestionById(Long id);

    public List<Question> getQuestionsByTestId(Long testId);

    public void editQuestion(Question question);

    public void deleteQuestionById(Long id);

}
