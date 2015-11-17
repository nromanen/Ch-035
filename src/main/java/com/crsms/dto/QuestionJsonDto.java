package com.crsms.dto;

import com.googlecode.jmapper.annotations.JMap;

/**
 * @author Petro Andriets
 */

public class QuestionJsonDto {

    public QuestionJsonDto() { }

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
