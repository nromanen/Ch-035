package com.crsms.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Petro Andriets, Valerii Motresku
 */

@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
    @SequenceGenerator(name = "crsms_gen", sequenceName = "test_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @Column(nullable = false)
    private Boolean available = false;

    @OneToMany(mappedBy = "test")
    @Cascade({CascadeType.ALL})
    private Set<Question> questions;

    public Test() {
    }

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

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "ID: " + id + "Title: " + name + "Module: " + module + "Available: " + getAvailable()
                + "Questions: " + getQuestions();
    }

}
