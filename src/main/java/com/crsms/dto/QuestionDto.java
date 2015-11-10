package com.crsms.dto;

import com.googlecode.jmapper.annotations.JMap;

import java.io.Serializable;

/**
 * @author Petro Andriets
 */

public class QuestionDto implements Serializable{

    public QuestionDto() {}

    @JMap
    private Long id;

    @JMap
    private String text;

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

}
