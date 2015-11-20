package com.crsms.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
		@NamedQuery(name = Question.DISABLE_ANSWERS,
					query = "UPDATE Answer answer SET answer.disable=true WHERE answer IN "
							+ "(SELECT answerList "
							+ "FROM Question question "
							+ "JOIN question.answers answerList "
							+ "WHERE question.id = :id)")
})
public class Question {
	public static final String GET_BY_TEST_ID = "Question.getByTestId";
	public static final String DISABLE_ANSWERS = "Question.disableAnswersByTest";
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
    
}
