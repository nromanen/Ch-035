package com.crsms.dto;

import com.googlecode.jmapper.annotations.JMap;

import java.io.Serializable;

/**
 * @author Petro Andriets
 */

public class AnswerDto implements Serializable{

    public AnswerDto() {}

    @JMap
    private Long id;

    @JMap
    private String text;

    @JMap
    private Boolean correct;

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

}
