package com.crsms.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.crsms.domain.Question;
import com.crsms.domain.Test;
import com.crsms.domain.TestResult;
import com.crsms.dto.QuestionFormDto;
import com.crsms.dto.UserAnswerFormDto;
import com.crsms.service.QuestionService;
import com.crsms.service.TestResultService;
import com.crsms.service.TestService;
import com.crsms.service.UserAnswerService;
import com.crsms.validator.TestFormValidator;

/**
 * @author Adriets Petro, St. Roman
 */

@Controller
@RequestMapping(value = "/courses/{courseId}/modules/{moduleId}/tests")
public class TestController {
	private static final String TESTS_PAGE = "tests";
	private static final String CREATE_TEST_PAGE = "createtest";
	private static final String SHOW_TEST_PAGE = "show_test";

	@Autowired(required = true)
	private TestService testService;
	
	@Autowired(required = true)
	private QuestionService questionService;
	
	@Autowired(required = true)
	private TestResultService testResultService;
	
	@Autowired
	private TestFormValidator testFormValidator;
	
	@Autowired
	private UserAnswerService userAnswerService;
	
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
	
	@RequestMapping(value = "/{testId}/show/{questionIndex}", method = RequestMethod.GET)
	public String showTest(@PathVariable("testId") Long testId, @PathVariable("questionIndex") Integer questionIndex, Model model, Principal principal) {
		//TODO: check: student subscribe on course
		//TODO: check: already pass this course
		Test test = testService.getById(testId);
		Long questionCount = questionService.getCountQestionsByTest(testId);
		Question question = questionService.getByTestByIndex(testId, questionIndex - 1);
		TestResult testResult = testResultService.getCurrent(testId, principal.getName());

		//TODO: check: you already have answer and move to srvise
		UserAnswerFormDto userAnswerFormDto = userAnswerService.getUserAnswerFormDto(testResult.getId(), question.getId());
		
		model.addAttribute("userAnswerFormDto", userAnswerFormDto);
		model.addAttribute("test", test);
		model.addAttribute("questionCount", questionCount);
		model.addAttribute("questionIndex", questionIndex);
		model.addAttribute("question", question);
		return SHOW_TEST_PAGE;
	}
	
	@RequestMapping(value = "/{testId}/show/{questionIndex}", method = RequestMethod.POST)
	public String addUserAnswer(@PathVariable Long courseId, @PathVariable Long moduleId, @RequestParam("nextIndex") Long nextIndex,
								@PathVariable("testId") Long testId, @PathVariable("questionIndex") Integer questionIndex, 
								UserAnswerFormDto userAnswerFormDto, Model model, Principal principal) {
		// TODO Check testresult is  for this user
		testResultService.save(userAnswerFormDto);
		
		Long questionCount = questionService.getCountQestionsByTest(testId);
		if(0 < nextIndex && nextIndex < questionCount){
			return redirectToQuestion(courseId, moduleId, testId, nextIndex);
		} else {
			return redirectToQuestion(courseId, moduleId, testId, questionCount);//TODO:finish
		}
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
	
	private String redirectToQuestion(Long courseId, Long moduleId, Long testId, Long questionIndex) {
		return "redirect:/courses/" + courseId + "/modules/" + moduleId + "/tests/" + testId + "/show/" + questionIndex;
	}
	
}
