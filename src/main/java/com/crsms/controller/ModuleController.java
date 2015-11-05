package com.crsms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crsms.domain.Module;
import com.crsms.dto.ModuleForm;
import com.crsms.service.CourseService;
import com.crsms.service.DtoService;
import com.crsms.service.ModuleService;
import com.crsms.validator.ModuleFormValidator;

/**
 * 
 * @author St. Roman
 *
 */

@Controller
@RequestMapping(value = {"/courses/{courseId}/modules"})
public class ModuleController {
	
	private static final String MODULES_VIEW = "modules";
	private static final String ADD_MODULE_VIEW = "createmodule";
	private static final String EDIT_MODULE_VIEW = "editmodule";
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private DtoService dtoService;;
	
	@Autowired
	private ModuleFormValidator validator;
	
	@InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String showModules(@PathVariable Long courseId, Model model) {
		List<Module> modules = moduleService.getAllByCourseId(courseId);
		model.addAttribute("modules", modules);
		return MODULES_VIEW;
	}
	
	@RequestMapping(value = {"/add"}, method = RequestMethod.GET)
	public String newModule(@PathVariable Long courseId, Model model) {
		Module module = new Module();
		
		ModuleForm moduleForm = dtoService.convert(module, ModuleForm.class, Module.class);
		moduleForm.setCourseId(courseId);
		
		model.addAttribute("moduleForm", moduleForm);
		return ADD_MODULE_VIEW;
	}
	
	@RequestMapping(value = {"/add"}, method = RequestMethod.POST)
	public String saveModule(@PathVariable Long courseId, 
							@Validated ModuleForm moduleForm, BindingResult result) {
		if (result.hasErrors()) {
			return ADD_MODULE_VIEW;
		}
		Module module = dtoService.convert(moduleForm, Module.class, ModuleForm.class);
		moduleService.add(courseId, module);
		return redirect(courseId);
	}	
	
	@RequestMapping(value = {"/{moduleId}/edit"}, method = RequestMethod.GET)
	public String editModule(@PathVariable Long courseId, 
							@PathVariable Long moduleId, Model model) {
		
		Module module = moduleService.getById(moduleId);
		
		ModuleForm moduleForm = dtoService.convert(module, ModuleForm.class, Module.class);
		moduleForm.setCourseId(courseId);
		
		model.addAttribute("moduleForm", moduleForm);
		return EDIT_MODULE_VIEW;
	}
	
	@RequestMapping(value = {"/{moduleId}/edit"}, method = RequestMethod.POST)
	public String updateModule(@PathVariable Long courseId, @PathVariable Long moduleId, 
								@Validated ModuleForm moduleForm, BindingResult result) {
		if (result.hasErrors()) {
			return EDIT_MODULE_VIEW;
		}

		Module module = dtoService.convert(moduleForm, Module.class, ModuleForm.class);
		moduleService.update(module);

		return redirect(courseId);
	}
	
	@RequestMapping(value = {"/{moduleId}/delete"}, method = RequestMethod.GET)
	public String deleteModule(@PathVariable Long courseId, @PathVariable Long moduleId) {
		moduleService.deleteById(moduleId);
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
