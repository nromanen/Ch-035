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
@RequestMapping(value = "course/{courseId}")
public class ModuleController {
	
	@Autowired
	ModuleService moduleService;
	
	@RequestMapping(value = {"/module"}, method = RequestMethod.GET)
	public String showModules(ModelMap model) {
		List<Module> modules = moduleService.getAll();
		model.addAttribute("modules", modules);
		return "modules";
	}
	
	@RequestMapping(value = {"/module/{moduleId}/edit"}, method = RequestMethod.GET)
	public String editModule(@PathVariable Long moduleId, ModelMap model) {
		Module module = moduleService.getById(moduleId);
		model.addAttribute("module", module);
		return "createmodule";
	}
	
	@RequestMapping(value = {"/module/{moduleId}/edit"}, method = RequestMethod.POST)
	public String updateModule(@PathVariable Long courseId, @PathVariable Long moduleId, Module module, ModelMap model) {
		if (moduleService.getById(moduleId) != null) {
			moduleService.update(module);
		}
		String redirect = "redirect:/course/" + courseId + "/module";
		return redirect;
	}
	
	@RequestMapping(value = {"/module/{moduleId}/delete"}, method = RequestMethod.GET)
	public String deleteModule(@PathVariable Long courseId, @PathVariable Long moduleId, ModelMap model) {
		moduleService.deleteById(moduleId);
		String redirect = "redirect:/course/" + courseId + "/module";
		return redirect;
	}
	
	@RequestMapping(value = {"/module/new"}, method = RequestMethod.GET)
	public String newModule(ModelMap model) {
		Module module = new Module();
		model.addAttribute("module", module);
		return "createmodule";
	}
	
	@RequestMapping(value = {"/module/new"}, method = RequestMethod.POST)
	public String saveModule(@PathVariable Long courseId, Module module, ModelMap model) {
		moduleService.save(module);
		String redirect = "redirect:/course/" + courseId + "/module";
		return redirect;
	}
	
	/*@RequestMapping(value = {"/createmodule"}, method = RequestMethod.GET)
	public String newModule(ModelMap model) {
		Module module = new Module();
		model.addAttribute("module", module);
		return "createmodule";
	}*/
	
	/*@RequestMapping(value = {"/createmodule"}, method = RequestMethod.POST)
	public String saveModule(Module module, ModelMap model) {
		service.save(module);
		model.addAttribute("message", "Module " + module.getName() + " created successfully");
		return "success";
	}*/
	
	/*@RequestMapping(value = {"/modules"}, method = RequestMethod.GET)
	public String showModules(ModelMap model) {
		List<Module> modules = service.getAll();
		model.addAttribute("modules", modules);
		return "modules";
	}*/
	
	/*@RequestMapping(value = {"/editmodule{id}"}, method = RequestMethod.GET)
	public String editModule(@PathVariable String id, ModelMap model) {
		Module module = service.getById(Long.parseLong(id));
		model.addAttribute("module", module);
		return "createmodule";
	}*/
	
	/*@RequestMapping(value = {"/editmodule{id}"}, method = RequestMethod.POST)
	public String updateModule(@PathVariable String id, Module module, ModelMap model) {
		if (service.getById(Long.parseLong(id)) != null) {
			service.update(module);
		}
		model.addAttribute("message", "Module " + module.getName() + " updated successfully");
		return "success";
	}*/
	
	/*@RequestMapping(value = {"/deletemodule{id}"}, method = RequestMethod.GET)
	public String deleteModule(@PathVariable String id, ModelMap model) {
		service.deleteById(Long.parseLong(id));
		return "redirect:/modules";
	}*/
	
	
}
