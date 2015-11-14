package com.crsms.dto;

import com.googlecode.jmapper.annotations.JMap;

public class VacancyJsonDto {
  @JMap
  private Long id;
  
  @JMap
  private String name;
  
  @JMap
  private String url;

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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
 
}
