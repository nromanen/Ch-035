package com.crsms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crsms.domain.Module;
import com.crsms.service.ModuleService;

/**
 * 
 * @author St. Roman
 *
 */

@Controller
@RequestMapping(value = {"/courses/{courseId}/modules"})
public class ModuleController {
	
	private final String MODULES_PAGE = "modules";
	private final String CREATE_MODULE_PAGE = "createmodule";
	
	@Autowired
	private ModuleService moduleService;
	
	//TODO РОЗІБРАТИСЬ ЧОМУ У ВАЛЄРИ АВТОМАТИЧНО ДОДАЄ СЛЕШ '/' В КІНЦІ УРЛА 'crsms/courses/1/modules' А В МЕНЕ НІ
	// crsms/courses/1/modules - видасть 404
	// crsms/courses/1/modules/ - працюватиме	
	@RequestMapping(value = {"/", "/all"}, method = RequestMethod.GET)
	public String showModules(Model model) {
		List<Module> modules = moduleService.getAll();
		model.addAttribute("modules", modules);
		return MODULES_PAGE;
	}
	
	@RequestMapping(value = {"/{moduleId}/edit"}, method = RequestMethod.GET)
	public String editModule(@PathVariable Long moduleId, Model model) {
		Module module = moduleService.getById(moduleId);
		model.addAttribute("module", module);
		return CREATE_MODULE_PAGE;
	}
	
	@RequestMapping(value = {"/{moduleId}/edit"}, method = RequestMethod.POST)
	public String updateModule(@PathVariable Long courseId, @PathVariable Long moduleId, 
								Module module, Model model) {
		if (moduleService.getById(moduleId) != null) {
			moduleService.update(module);
		}
		return redirect(courseId);
	}
	
	@RequestMapping(value = {"/{moduleId}/delete"}, method = RequestMethod.GET)
	public String deleteModule(@PathVariable Long courseId, @PathVariable Long moduleId, Model model) {
		moduleService.deleteById(moduleId);
		return redirect(courseId);
	}
	
	@RequestMapping(value = {"/add"}, method = RequestMethod.GET)
	public String newModule(Model model) {
		Module module = new Module();
		model.addAttribute("module", module);
		return CREATE_MODULE_PAGE;
	}
	
	@RequestMapping(value = {"/add"}, method = RequestMethod.POST)
	public String saveModule(@PathVariable Long courseId, Module module, Model model) {
		moduleService.add(courseId, module);
		return redirect(courseId);
	}	
	
	/**
	 * Returns redirection path
	 * @param courseId course id to be put into path
	 * @return String path, like: "redirect:/courses/_courseId_here_/modules"
	 */
	private String redirect(Long courseId) {
		return "redirect:/courses/" + courseId + "/modules/";
	}
}
