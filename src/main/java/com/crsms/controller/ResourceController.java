package com.crsms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.WebResourceRoot.ResourceSetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;

import com.crsms.domain.Resource;
import com.crsms.service.ResourceService;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Controller
@RequestMapping(value = {"/course/{courseId}/module/{moduleId}/resources",
		"/resources"})
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value = { "/", "/all" }, method = RequestMethod.GET)
	public String allResources(Model model) {
		List<Resource> resources = resourceService.getAll();
		model.addAttribute("resources", resources);
		return "resources";
	}
	
	@RequestMapping(value = {"/add"}, method = RequestMethod.GET)
	public String addResource(Model model) {
		Resource resource = new Resource();
		model.addAttribute("resource", resource);
		model.addAttribute("resourceTypeValues", Resource.Type.values());
		return "add";
	}
	
	@RequestMapping(value = {"/add"}, method = RequestMethod.POST)
	public String saveResource(Resource resource, Model model) {
		resourceService.save(resource);
		resource = new Resource();
		model.addAttribute("resource", resource);
		model.addAttribute("resourceTypeValues", Resource.Type.values());
		String success = "Resource successfully added";
		model.addAttribute("success", success);
		return "add";
	}
	
	@RequestMapping(value = {"/{id}/delete"}, method = RequestMethod.GET)
	public String desleteResource(@PathVariable Long id, HttpServletRequest request, Model model) {
		resourceService.deleteById(id);
		String controllerContextPath = request.getServletPath().replaceFirst("\\/\\d+\\/delete$", "");
		//String success = "Resource successfully removed";
		//model.addAttribute("success", success);
		return "redirect:" + controllerContextPath + "/all";
	}
	
}
