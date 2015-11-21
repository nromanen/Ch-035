package com.crsms.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Petro Andriets, Valerii Motresku
 */

@Entity
@Table(name = "answer")
@NamedQueries(@NamedQuery(name = Answer.GET_BY_QUESTION_ID, query = "SELECT answers FROM Question q WHERE q.id = :id"))
public class Answer {
	public static final int MAX_TEXT_LENGTH = 200;
	public static final String GET_BY_QUESTION_ID = "Answer.getByQuestionId";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "answer_id_seq",  allocationSize = 1)
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	@Size(max = 200)
	private String text;
	
	@Column(nullable = false)
	private Boolean correct = false;
	
	@Column(nullable = false)
	private Boolean disable = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public Boolean getDisable() {
		return disable;
	}
	
	public void disable() {
		this.disable = true;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
	
}
