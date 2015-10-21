package com.crsms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crsms.domain.Module;
import com.crsms.service.ModuleService;

@Controller
public class ModuleController {
	
	@Autowired
	ModuleService service;
	
	@RequestMapping(value = {"/createmodule"}, method = RequestMethod.GET)
	public String newModule(ModelMap model) {
		Module module = new Module();
		model.addAttribute("module", module);
		return "createmodule";
	}
	
	@RequestMapping(value = {"/createmodule"}, method = RequestMethod.POST)
	public String saveModule(Module module, ModelMap model) {
		service.save(module);
		model.addAttribute("message", "Module " + module.getName() + " created successfully");
		return "success";
	}
	
	@RequestMapping(value = {"/modules"}, method = RequestMethod.GET)
	public String showModules(ModelMap model) {
		List<Module> modules = service.getAll();
		model.addAttribute("modules", modules);
		return "modules";
	}
}
