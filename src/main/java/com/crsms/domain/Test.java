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
@Table(name = "test")
@NamedQueries({ @NamedQuery(name = Test.GET_ALL, query = "FROM Test"),
			  	@NamedQuery(name = Test.GET_BY_MODULE_ID, query = "FROM Test WHERE module_id = :id order by id asc")
				})
public class Test {
	public static final String GET_ALL = "Test.getAll";
	public static final String GET_BY_MODULE_ID = "Test.getByModuleId";
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Question> questions;

    public Test() {}

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
