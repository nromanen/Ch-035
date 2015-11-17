package com.crsms.dao;

import com.crsms.domain.Answer;

import java.util.List;

/**
 * @author Petro Andriets
 */

public interface AnswerDao extends BaseDao<Answer> {

    public List<Answer> getAnswersByQuestionId(Long id);

    public void deleteAnswerById(Long id);

	void disable(Answer answer);
	
}
