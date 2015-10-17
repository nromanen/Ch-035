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
@Table(name="test_result")
public class TestResult {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "test_result_id_seq", allocationSize = 1)
	private Long id;
	
	@OneToOne
	@Cascade({CascadeType.ALL})
    @JoinColumn(name="test_id")
	private Test test;
	
	
	private Boolean complete = false;
	
	// TODO field Result. How to calculate.
	// private Type Result;
	
	@OneToOne
	@Cascade({CascadeType.ALL})
    @JoinColumn(name="user_id")
	private User user;
	
	public TestResult() {}

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

}
