package com.crsms.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Set;


/**
 * @author Petro Andriets, Valerii Motresku
 */

@Entity
@Table(name = "question")
@NamedQueries(@NamedQuery(name = Question.GET_BY_TEST_ID, query = "SELECT questions FROM Test t WHERE t.id = :id"))
public class Question {
	public static final String GET_BY_TEST_ID = "Question.getByTestId";
    public static final int MAX_TEXT_LENGTH = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
    @SequenceGenerator(name = "crsms_gen", sequenceName = "question_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(max = 1000)
    private String text;
    
    @Column(nullable = false)
	private Boolean disable = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //, orphanRemoval = true
    private Set<Answer> answers;

    public Question() {
    }

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

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
    
}
