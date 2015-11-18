package com.crsms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Entity
@Table(name="user_answer")
public class UserAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "user_answer_id_seq", allocationSize = 1)
	private Long id;
	
	@OneToOne
	@Cascade({CascadeType.ALL})
    @JoinColumn(name="question_id")
	private Question question;
	
	@OneToOne
	@Cascade({CascadeType.ALL})
    @JoinColumn(name="answer_id")
	private Answer answer;
	
	@OneToOne
	@Cascade({CascadeType.ALL})
    @JoinColumn(name="test_result_id")
	private TestResult testResult;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public TestResult getTestResult() {
		return testResult;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}

}
