package com.crsms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "user_answer")
@NamedQueries({
	@NamedQuery(name = UserAnswer.DELETE_BY_TEST_RESULT_QUESTION,
				query = "DELETE UserAnswer userAnswer "
						+ "WHERE userAnswer.testResult.id = :testResultId AND userAnswer.question.id = :questionId")
})
public class UserAnswer {
	public static final String DELETE_BY_TEST_RESULT_QUESTION = "UserAnswer.deleteByTestResultAndQuestion";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "user_answer_id_seq", allocationSize = 1)
	private Long id;
	
	@ManyToOne
	@Cascade({CascadeType.ALL})
    @JoinColumn(name = "question_id")
	private Question question;
	
	@ManyToOne
	@Cascade({CascadeType.ALL})
    @JoinColumn(name = "answer_id")
	private Answer answer;
	
	@OneToOne
	@Cascade({CascadeType.ALL})
    @JoinColumn(name = "test_result_id")
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
