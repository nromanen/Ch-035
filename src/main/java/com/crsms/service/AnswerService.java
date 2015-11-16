package com.crsms.service;

import com.crsms.domain.Answer;

import java.util.List;

/**
 * @author Andriets Petro
 */

public interface AnswerService {

    public void createAnswer(Long questionId, Answer answer);

    public Answer getAnswerById(Long id);

    public List<Answer> getAnswersByQuestionId(Long questionId);

    public void editAnswer(Answer answer);
    
    public void disable(Long answerId);
    
    public void disable(Answer answer);

    //public void deleteAnswerById(Long id);

}
