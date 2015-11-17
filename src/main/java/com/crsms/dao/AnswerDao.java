package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Answer;

/**
 * @author Petro Andriets
 */

public interface AnswerDao extends BaseDao<Answer> {

    List<Answer> getAnswersByQuestionId(Long id);

    void deleteAnswerById(Long id);

	void disable(Answer answer);
	
}
