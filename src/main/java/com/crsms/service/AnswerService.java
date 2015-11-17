package com.crsms.service;

import java.util.List;

import com.crsms.domain.Answer;

/**
 * @author Andriets Petro
 */

public interface AnswerService extends BaseService<Answer> {

    void createAnswer(Long questionId, Answer answer);

    List<Answer> getAnswersByQuestionId(Long questionId);
    
    void disable(Long answerId);
    
    void disable(Answer answer);

}
