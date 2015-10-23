package com.crsms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crsms.domain.Course;
import com.crsms.domain.Module;
import com.crsms.service.ModuleService;

@Controller
@RequestMapping(value = "courses/{courseId}/modules")
public class ModuleController {
	
	@Autowired
	ModuleService moduleService;
	
	@RequestMapping(value = {""}, method = RequestMethod.GET)
	public String showModules(ModelMap model) {
		List<Module> modules = moduleService.getAll();
		model.addAttribute("modules", modules);
		return "modules";
	}
	
	@RequestMapping(value = {"/{moduleId}/edit"}, method = RequestMethod.GET)
	public String editModule(@PathVariable Long moduleId, ModelMap model) {
		Module module = moduleService.getById(moduleId);
		model.addAttribute("module", module);
		return "createmodule";
	}
	
	@RequestMapping(value = {"/{moduleId}/edit"}, method = RequestMethod.POST)
	public String updateModule(@PathVariable Long courseId, @PathVariable Long moduleId, 
								Module module, ModelMap model) {
		if (moduleService.getById(moduleId) != null) {
			moduleService.update(module);
		}
		return redirect(courseId);
	}
	
	@RequestMapping(value = {"/{moduleId}/delete"}, method = RequestMethod.GET)
	public String deleteModule(@PathVariable Long courseId, @PathVariable Long moduleId, ModelMap model) {
		moduleService.deleteById(moduleId);
		return redirect(courseId);
	}
	
	@RequestMapping(value = {"/add"}, method = RequestMethod.GET)
	public String newModule(ModelMap model) {
		Module module = new Module();
		model.addAttribute("module", module);
		return "createmodule";
	}
	
	@RequestMapping(value = {"/add"}, method = RequestMethod.POST)
	public String saveModule(@PathVariable Long courseId, Module module, ModelMap model) {
		moduleService.save(module);
		return redirect(courseId);
	}	
	
	/**
	 * Returns redirection path
	 * @param courseId
	 * @return String 
	 */
	private String redirect(Long courseId) {
		return "redirect:/courses/" + courseId + "/modules";
	}
}
