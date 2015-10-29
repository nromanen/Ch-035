package com.crsms.controller;


import java.util.List;

import com.crsms.domain.Test;
import com.crsms.service.TestService;
import com.crsms.validator.TestFormValidator;

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

/**
 * @author Adriets Petro, St. Roman
 */

@Controller
@RequestMapping(value = "/courses/{courseId}/modules/{moduleId}/tests")
public class TestController {

	@Autowired(required = true)
	private TestService testService;
	
	@Autowired
	private TestFormValidator formValidator;
	
	@InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(formValidator);
    }

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addTest(@PathVariable Long courseId, @PathVariable Long moduleId, 
						  @Validated Test test, BindingResult result) {
		if (result.hasErrors()) {
			return "createtest";
		} else {
			testService.createTest(moduleId, test);
		}
		return redirect(courseId, moduleId);
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String newTest(Model model) {
		Test test = new Test();
		model.addAttribute("test", test);
		return "createtest";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String updateTest(@PathVariable Long courseId, @PathVariable Long moduleId, 
							 @PathVariable Long id, @Validated Test test, BindingResult result) {
		if (result.hasErrors()) {
			return "createtest";
		} else if (testService.getTestById(id) != null) {
			testService.editTest(test);
		}
		return redirect(courseId, moduleId);

	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editTest(@PathVariable("id") Long id, Model model) {
		Test tempTest = testService.getTestById(id);
		model.addAttribute("test", tempTest);
		return "createtest";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllTestsByModuleId(@PathVariable Long moduleId, Model model) {
		List<Test> tests = testService.getAllByModuleId(moduleId);
		model.addAttribute("tests", tests);
		return "tests";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String deleteTestById(@PathVariable Long courseId, @PathVariable Long moduleId, 
								 @PathVariable("id") Long id) {
		testService.deleteTestById(id);
		return redirect(courseId, moduleId);
	}

	/*
	 * Method returns path redirection.
	 */
	private String redirect(Long courseId, Long moduleId) {
		return "redirect:/courses/" + courseId + "/modules/" + moduleId + "/tests/";
	}

}
