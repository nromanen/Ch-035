package com.crsms.dto;

import java.util.List;

import com.crsms.domain.User;

public class CoursesViewDto {
	private List<CourseViewDto> courseViewDtos;
	
	private Integer allCurses;
	private Integer passedCurses;
	private Integer failedCurses;
	private Integer continuedCurses;
	private Double score;
	private Double maxScore;
	private Double progress;
	
	private User user;

	public List<CourseViewDto> getCourseViewDtos() {
		return courseViewDtos;
	}

	public void setCourseViewDtos(List<CourseViewDto> courseViewDtos) {
		this.courseViewDtos = courseViewDtos;
	}

	public Integer getPassedCurses() {
		return passedCurses;
	}

	public void setPassedCurses(Integer passedCurses) {
		this.passedCurses = passedCurses;
	}

	public Integer getFailedCurses() {
		return failedCurses;
	}

	public void setFailedCurses(Integer failedCurses) {
		this.failedCurses = failedCurses;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Double maxScore) {
		this.maxScore = maxScore;
	}

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}

	public Integer getContinuedCurses() {
		return continuedCurses;
	}

	public void setContinuedCurses(Integer continuedCurses) {
		this.continuedCurses = continuedCurses;
	}

	public Integer getAllCurses() {
		return allCurses;
	}

	public void setAllCurses(Integer allCurses) {
		this.allCurses = allCurses;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
