package com.crsms.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * @author Petro Andriets, Valerii Motresku
 */

@Entity
@Table(name = "question")
@NamedQueries({
		@NamedQuery(name = Question.GET_BY_TEST_ID,
					query = "SELECT questions FROM Test t WHERE t.id = :id"),
		@NamedQuery(name = Question.GET_BY_TEST, //TODO: change name
					query = "SELECT question FROM Test test "
							+ "JOIN test.questions question "
							+ "WHERE test.id = :id and question.disable = false "
							+ "ORDER BY question.id"),
		@NamedQuery(name = Question.GET_BY_ANSWER,
					query = "SELECT question FROM Question question "
							+ "JOIN question.answers answer "
							+ "WHERE answer.id = :id"),
		@NamedQuery(name = Question.GET_QUESTION_COUNT_BY_TEST, 
					query = "SELECT count(*) FROM Test test "
							+ "JOIN test.questions question "
							+ " WHERE test.id = :id")
})
public class Question {
	public static final String GET_BY_TEST_ID = "Question.getByTestId";
	public static final String GET_BY_TEST = "Question.getByTest";
	public static final String GET_BY_ANSWER = "Question.getByAnswer";
	public static final String GET_QUESTION_COUNT_BY_TEST = "Question.getByQuestionCountByTest";
    public static final int MAX_TEXT_LENGTH = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
    @SequenceGenerator(name = "crsms_gen", sequenceName = "question_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(max = MAX_TEXT_LENGTH)
    private String text;
    
    @Column(nullable = false)
	private Boolean disable = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Answer> answers;

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
    
    public void removeAnswer(Answer answer) {
    	this.answers.remove(answer);
    }

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
	
	public void disable() {
		this.disable = true;
		for(Answer answer : this.answers){
			answer.disable();
		}
	}
    
}
