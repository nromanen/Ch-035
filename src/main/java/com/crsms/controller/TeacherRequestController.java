package com.crsms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crsms.service.TeacherRequestService;

@RestController
@RequestMapping("/api/teacher/request")
public class TeacherRequestController {
	
	@Autowired
	private TeacherRequestService teacherRequestService;
	
	@RequestMapping("/{id}/approve")
	public String approve(@PathVariable("id") Long requestId) {
		teacherRequestService.approve(requestId);
		return "Success";
	}
	
	@RequestMapping("/{id}/decline")
	public String decline(@PathVariable("id") Long requestId) {
		teacherRequestService.decline(requestId);
		return "Success";
	}

}
