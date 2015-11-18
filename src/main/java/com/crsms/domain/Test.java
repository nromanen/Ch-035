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
@Table(name = "test")
@NamedQueries({ 
	@NamedQuery(name = Test.GET_ALL,
				query = "FROM Test"),
	@NamedQuery(name = Test.GET_BY_MODULE_ID,
				query = "SELECT tests FROM Module m WHERE m.id = :id"),
  	@NamedQuery(name = Test.GET_BY_QUESTION, 
  				query = "SELECT test FROM Test test "
  						+ "JOIN test.questions question "
  						+ " WHERE question.id = :id"),
	@NamedQuery(name = Test.DISABLE_QUESTIONS,
				query = "UPDATE Question question SET question.disable=true WHERE question IN "
						+ "(SELECT questionList "
						+ "FROM Test test "
						+ "JOIN test.questions questionList "
						+ "WHERE test.id = :id)"),
	@NamedQuery(name = Test.DISABLE_ANSWERS,
				query = "UPDATE Answer answer SET answer.disable=true WHERE answer IN "
						+ "(SELECT answerList "
						+ "FROM Test test "
						+ "JOIN test.questions questionList "
						+ "JOIN questionList.answers answerList "
						+ "WHERE test.id = :id)")
})
public class Test {
	public static final String GET_ALL = "Test.getAll";
	public static final String GET_BY_MODULE_ID = "Test.getByModuleId";
	public static final String GET_BY_QUESTION = "Test.getByQuestion";
	public static final String DISABLE_QUESTIONS = "Test.disableQuestionsByTest";
	public static final String DISABLE_ANSWERS = "Test.disableAnswersByTest";
	public static final int MAX_NAME_LENGTH = 100;
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
    @SequenceGenerator(name = "crsms_gen", sequenceName = "test_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
	@Size(max = MAX_NAME_LENGTH)
    private String name;

    @Column(nullable = false)
    private Boolean available = false;
    
    @Column(nullable = false)
	private Boolean disable = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Question> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
    
    public void removeQuestion(Question question) {
        this.questions.remove(question);
    }

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
    
}
