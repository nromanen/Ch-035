package com.crsms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crsms.domain.Group;
import com.crsms.dto.UserIdFNameLNameEmailDto;
import com.crsms.service.GroupService;

@Controller
@RequestMapping("/private/courses/{courseId}/groups/{groupId}/students")
public class PrivateGroupStudentsController {
	public static final String STUDENTS_VIEW = "students";
	public static final String ADD_STUDENTS_VIEW = "addstudents";
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStudents(@PathVariable Long groupId,
			@RequestParam(required = false, defaultValue = "firstName") String sortBy,
			@RequestParam(required = false, defaultValue = "asc") String order,
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "5") Integer limit, Model model) {
		Group group = groupService.getById(groupId);
		List<UserIdFNameLNameEmailDto> students =
				groupService.getStudentsFromGroupPaginated(groupId, sortBy, order, page, limit);
		model.addAttribute("count", groupService.getStudentsCountFromGroup(groupId));
		model.addAttribute("limit", limit);
		model.addAttribute("students", students);
		model.addAttribute("headerTitle", group.getName());
		model.addAttribute("pageTitle", group.getName());
		return STUDENTS_VIEW;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addStudents(@PathVariable Long courseId, @PathVariable Long groupId,
			Model model) {
		Group group = groupService.getById(groupId);
		model.addAttribute("headerTitle", group.getName());
		return ADD_STUDENTS_VIEW;
	}
	
	@RequestMapping(value = "/{studentId}/remove", method = RequestMethod.GET)
	public String removeStudentFromGroup(@PathVariable Long courseId, @PathVariable Long groupId,
			@PathVariable Long studentId) {
		groupService.unsubscribe(groupId, studentId);
		return redirectToStudentsList(courseId, groupId);
	}
	
	private String redirectToStudentsList(Long courseId, Long groupId) {
		return "redirect:/private/courses/" + courseId + "/groups/" + groupId + "/students/";
	}
}
