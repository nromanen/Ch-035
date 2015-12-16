package com.crsms.controller;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crsms.domain.Group;
import com.crsms.dto.GroupFormDto;
import com.crsms.service.DtoService;
import com.crsms.service.GroupService;

@Controller
@RequestMapping("/private/courses/{courseId}/groups")
public class PrivateGroupController {
	public static final String GROUPS_VIEW = "groups";
	public static final String ADD_GROUP_VIEW = "addgroup";
	public static final String EDIT_GROUP_VIEW = "editgroup";
	
	@Autowired
	private DtoService droService;
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getGroups(@PathVariable Long courseId, Model model) {
		model.addAttribute("groups", groupService.getAllByCourseId(courseId));
		return GROUPS_VIEW;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGroup(@PathVariable Long courseId, Model model) {
		GroupFormDto groupFormDto = droService.convert(new Group(),
				GroupFormDto.class, Group.class);
		groupFormDto.setCourseId(courseId);
		groupFormDto.setStartDate(DateTime.now());
		model.addAttribute("groupFormDto", groupFormDto);
		return ADD_GROUP_VIEW;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addGroupSubmit(@PathVariable Long courseId, GroupFormDto groupFormDto) {
		Group group = droService.convert(groupFormDto, Group.class, GroupFormDto.class);
		groupService.save(courseId, group);
		return redirectToGroupsList(courseId);
	}
	
	@RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
	public String editGroup(@PathVariable Long courseId, @PathVariable Long groupId, Model model) {
		Group group = groupService.getById(groupId);
		GroupFormDto groupFormDto = droService.convert(group, GroupFormDto.class, Group.class);
		groupFormDto.setCourseId(courseId);
		model.addAttribute("groupFormDto", groupFormDto);
		return EDIT_GROUP_VIEW;
	}
	
	@RequestMapping(value = "/{groupId}", method = RequestMethod.POST)
	public String editGroupSubmit(@PathVariable Long courseId, @PathVariable Long groupId,
			GroupFormDto groupFormDto) {
		Group group = droService.convert(groupFormDto, Group.class, GroupFormDto.class);
		groupService.update(courseId, group);
		return redirectToGroupsList(courseId);
	}
	
	@RequestMapping(value = "/{groupId}/delete", method = RequestMethod.GET)
	public String editGroupSubmit(@PathVariable Long courseId, @PathVariable Long groupId) {
		groupService.deleteById(groupId);
		return redirectToGroupsList(courseId);
	}
	
	private String redirectToGroupsList(Long courseId) {
		return "redirect:/private/courses/" + courseId + "/groups/";
	}
}
