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

import com.crsms.domain.Test;
import com.crsms.dto.QuestionFormDto;
import com.crsms.service.TestService;
import com.crsms.validator.TestFormValidator;

/**
 * @author Adriets Petro, St. Roman
 */

@Controller
@RequestMapping(value = "/private/courses/{courseId}/modules/{moduleId}/tests")
public class PrivateTestController {
	private static final String TESTS_PAGE = "tests";
	private static final String CREATE_TEST_PAGE = "createtest";
	

	@Autowired(required = true)
	private TestService testService;
	
	@Autowired
	private TestFormValidator testFormValidator;
	
	@InitBinder(value = "test")
    private void initBinder(WebDataBinder binder) {
		binder.setValidator(testFormValidator);
    }
	
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

	@RequestMapping(value = "/add", method = RequestMethod.GET)
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
		} else if (testService.getById(id) != null) {
			testService.update(test);
		}
		return redirect(courseId, moduleId);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editTest(@PathVariable("id") Long id, Model model) {
		Test tempTest = testService.getById(id);
		model.addAttribute("test", tempTest);
		return CREATE_TEST_PAGE;
	}
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllTestsByModuleId(@PathVariable Long moduleId, Model model) {
		List<Test> tests = testService.getAllByModuleId(moduleId);
		model.addAttribute("tests", tests);
		model.addAttribute("question", new QuestionFormDto());
		return TESTS_PAGE;
	}

	/*
	 * Method returns path redirection.
	 */
	private String redirect(Long courseId, Long moduleId) {
		return "redirect:/courses/" + courseId + "/modules/" + moduleId + "/tests/";
	}
	
}
