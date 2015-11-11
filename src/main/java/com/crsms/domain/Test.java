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
@Table(name = "test")
@NamedQueries(@NamedQuery(name = Test.GET_ALL, query = "FROM Test"))
public class Test {
	public static final String GET_ALL = "Test.getAll";
	public static final int MAX_NAME_LENGTH = 100;
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
    @SequenceGenerator(name = "crsms_gen", sequenceName = "test_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = true)
    @NotNull
	@Size(max = MAX_NAME_LENGTH)
    private String name;

    @Column(nullable = false)
    private Boolean available = false;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Question> questions;

    public Test() { }

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
}
