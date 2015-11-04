package com.crsms.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Petro Andriets, Valerii Motresku
 */

@Entity
@Table(name="question")
public class Question {
	public static final int MAX_TEXT_LENGTH = 1000;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "question_id_seq", allocationSize = 1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "test_id")
	private Test test;

	@NotNull
	@Size(max = 1000)
	private String text;
	
	@OneToMany(mappedBy="question", fetch = FetchType.LAZY) //, orphanRemoval = true
	//@Cascade({CascadeType.ALL})
	private Set<Answer> answers;

	public Question() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String question) {
		this.text = question;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

}
