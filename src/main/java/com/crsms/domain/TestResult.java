package com.crsms.domain;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Entity
@Table(name = "test_result")
@NamedQueries({
	@NamedQuery(name = TestResult.GET_CURRENT,
				query = "FROM TestResult testResult "
						+ "WHERE testResult.test.id = :testId AND "
						+ "testResult.user.id = :userId"),
	@NamedQuery(name = TestResult.GET_BY_ID_AND_USER,
				query = "FROM TestResult testResult "
						+ "WHERE testResult.id = :testResultId AND testResult.user.id = :userId"),
	@NamedQuery(name = TestResult.COUNT_CORRECT_ANSWER,
				query = "SELECT COUNT(*) "
						+ "FROM UserAnswer userAnswer "
						+ "WHERE userAnswer.testResult.id = :testResultId AND userAnswer.answer.correct = true"),
	@NamedQuery(name = TestResult.COUNT_INCORRECT_ANSWER,
				query = "SELECT COUNT(*) "
						+ "FROM UserAnswer userAnswer "
						+ "WHERE userAnswer.testResult.id = :testResultId AND userAnswer.answer.correct = false"),
	@NamedQuery(name = TestResult.GET_BY_TEST_ID_AND_USERS,
		query = "SELECT tr "
			+ "FROM TestResult tr "
			+ "WHERE tr.test.id = :testId AND tr.user.id IN :users")
})
public class TestResult {
	public static final String GET_CURRENT = "TestResult.getCurrent";
	public static final String GET_BY_ID_AND_USER = "TestResult.getByIdAndUser";
	public static final String COUNT_CORRECT_ANSWER = "TestResult.countCorrectAnswer";
	public static final String COUNT_INCORRECT_ANSWER = "TestResult.countIncorrectAnswer";
	public static final String GET_BY_TEST_ID_AND_USERS = "TestResult.getByTestIdAndGroupId";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "test_result_id_seq", allocationSize = 1)
	private Long id;
	
	@ManyToOne
	@Cascade({CascadeType.ALL})
    @JoinColumn(name = "test_id")
	private Test test;
	
	private Boolean complete = false;
	// private Type Result;
	
	@ManyToOne
	@Cascade({CascadeType.ALL})
    @JoinColumn(name = "user_id")
	private User user;
	
	@Column(nullable = false)
	private Double score;

	@Column
	private Boolean pass;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}
}
