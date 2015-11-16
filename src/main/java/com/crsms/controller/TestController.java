package com.crsms.controller;


import com.crsms.domain.Question;
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

import java.util.List;

/**
 * @author Adriets Petro, St. Roman
 */

@Controller
@RequestMapping(value = "/courses/{courseId}/modules/{moduleId}/tests")
public class TestController {
	private final String TESTS_PAGE = "tests";
	private final String CREATE_TEST_PAGE = "createtest";

	@Autowired(required = true)
	private TestService testService;
	
	@Autowired
	private TestFormValidator testFormValidator;

	public TestController() {}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addTest(@PathVariable Long courseId, @PathVariable Long moduleId, 
						  @Validated Test test, BindingResult result) {
		if (result.hasErrors()) {
			return CREATE_TEST_PAGE;
		} else {
			testService.createTest(moduleId, test);
		}
		return redirect(courseId, moduleId);
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String newTest(Model model) {
		Test test = new Test();
		model.addAttribute("test", test);
		return CREATE_TEST_PAGE;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String updateTest(@PathVariable Long courseId, @PathVariable Long moduleId, 
							 @PathVariable Long id, @Validated Test test, BindingResult result) {
		if (result.hasErrors()) {
			return CREATE_TEST_PAGE;
		} else if (testService.getTestById(id) != null) {
			testService.editTest(test);
		}
		return redirect(courseId, moduleId);

	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editTest(@PathVariable("id") Long id, Model model) {
		Test tempTest = testService.getTestById(id);
		model.addAttribute("test", tempTest);
		return CREATE_TEST_PAGE;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllTestsByModuleId(@PathVariable Long moduleId, Model model) {
		List<Test> tests = testService.getAllByModuleId(moduleId);
		model.addAttribute("tests", tests);
		model.addAttribute("question", new Question());
		return TESTS_PAGE;
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
	
	/*
	 * Method for form validation binding.
	 */
	@InitBinder(value="test")
    private void initBinder(WebDataBinder binder) {
		binder.setValidator(testFormValidator);
    }

}
