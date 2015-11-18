package com.crsms.dto;

import java.util.Set;

import com.crsms.domain.Answer;
import com.crsms.domain.Question;

/**
 * @author Petro Andriets
 */
public class QuestionFormDto {
	
	//TODO dto modificators.
	public Question question;
	public Set<Answer> answers;
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Set<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
	
}
