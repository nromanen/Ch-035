package com.crsms.service;

import com.crsms.domain.Answer;

import java.util.List;

/**
 * @author Andriets Petro
 */

public interface AnswerService extends BaseService<Answer> {

    public void createAnswer(Long questionId, Answer answer);

    public List<Answer> getAnswersByQuestionId(Long questionId);
    
    public void disable(Long answerId);
    
    public void disable(Answer answer);

}
