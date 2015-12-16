package com.crsms.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crsms.service.GroupService;

@Controller
@RequestMapping(value = "/groups")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/{groupId}/enroll", method = RequestMethod.GET)
	public String subscribe(@PathVariable Long groupId, Principal principal) {
		groupService.subscribe(groupId, principal.getName());
		return CourseController.MY_COURSES_REDIRECT;
	}
	
	@RequestMapping(value = "/{groupId}/leave", method = RequestMethod.GET)
	public String unsubscribe(@PathVariable Long groupId, Principal principal) {
		groupService.unsubscribe(groupId, principal.getName());
		return CourseController.MY_COURSES_REDIRECT;
	}
	
}
