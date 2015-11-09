package com.crsms.dao;

import com.crsms.domain.Answer;

import java.util.List;

/**
 * @author Petro Andriets
 */

public interface AnswerDao {

    public void saveAnswer(Answer answer);

    public Answer getAnswerById(Long id);

    public List<Answer> getAnswersByQuestionId(Long id);

    public void updateAnswer(Answer answer);

    public void deleteAnswerById(Long id);
}
