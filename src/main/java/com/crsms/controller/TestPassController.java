package com.crsms.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crsms.domain.Question;
import com.crsms.domain.Test;
import com.crsms.domain.TestResult;
import com.crsms.dto.UserAnswerAndQuestionDto;
import com.crsms.dto.UserAnswerFormDto;
import com.crsms.service.QuestionService;
import com.crsms.service.TestResultService;
import com.crsms.service.TestService;
import com.crsms.service.UserAnswerService;

@Controller
@RequestMapping(value = "/courses/{courseId}/modules/{moduleId}/tests")
public class TestPassController {
	private static final String SHOW_TEST_PAGE = "show_test";
	private static final String SHOW_TEST_RESULT_PAGE = "show_test_result";
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private TestResultService testResultService;
	
	@Autowired
	private UserAnswerService userAnswerService;
	
	@RequestMapping(value = "/{testId}/testresult/{testResultId}", method = RequestMethod.GET)
	public String showTestResult(
			@PathVariable("testResultId") Long testResultId, Model model, Principal principal
	) {
		TestResult testResult = testResultService.getById(testResultId, principal.getName());
		Double testResaltScore = testResultService.getScore(testResult.getId());
		
		List<UserAnswerAndQuestionDto> userAnswerAndQuestionList 
			= testResultService.getUserAnswerAndQuestionList(testResult.getId());
		
		model.addAttribute("userAnswerAndQuestionList", userAnswerAndQuestionList);
		model.addAttribute("testResaltScore", testResaltScore);
		return SHOW_TEST_RESULT_PAGE;
	}
	
	@RequestMapping(value = "/{testId}/show/{questionIndex}", method = RequestMethod.GET)
	public String showTest(
			@PathVariable Long courseId, @PathVariable Long moduleId, 
			@PathVariable("testId") Long testId, 
			@PathVariable("questionIndex") Integer questionIndex, Model model, Principal principal
	) {
		//TODO: check: student subscribe on course
		Test test = testService.getById(testId);
		Long questionCount = questionService.getCountQestionsByTest(testId);
		Question question = questionService.getByTestByIndex(testId, questionIndex - 1);
		TestResult testResult = testResultService.getCurrent(testId, principal.getName());
		
		if(testResult.getComplete())
			return redirectToTestResult(courseId, moduleId, testId, testResult.getId());
		
		List<Boolean> isAnsweredQuestions = userAnswerService.getIsAnsweredQuestions(test.getId(), testResult.getId(), questionCount);

		UserAnswerFormDto userAnswerFormDto = userAnswerService.getUserAnswerFormDto(testResult.getId(), question.getId());
		
		model.addAttribute("isAnsweredQuestions", isAnsweredQuestions);
		model.addAttribute("userAnswerFormDto", userAnswerFormDto);
		model.addAttribute("test", test);
		model.addAttribute("questionCount", questionCount);
		model.addAttribute("questionIndex", questionIndex);
		model.addAttribute("question", question);
		return SHOW_TEST_PAGE;
	}
	
	@RequestMapping(value = "/{testId}/show/{questionIndex}", method = RequestMethod.POST)
	public String addUserAnswer(
			@PathVariable Long courseId, @PathVariable Long moduleId, 
			@RequestParam("nextIndex") Long nextIndex,
			@RequestParam("finished") Boolean finished, @PathVariable("testId") Long testId, 
			@PathVariable("questionIndex") Integer questionIndex, 
			UserAnswerFormDto userAnswerFormDto, Model model, Principal principal
	) {
		testResultService.save(userAnswerFormDto, principal.getName());
		
		if(finished) {
			testResultService.complete(userAnswerFormDto.getTestResultId());
			return redirectToTestResult(courseId, moduleId, testId, userAnswerFormDto.getTestResultId());
		}
		
		Long questionCount = questionService.getCountQestionsByTest(testId);
		if(0 < nextIndex && nextIndex < questionCount){
			return redirectToQuestion(courseId, moduleId, testId, nextIndex);
		} else {
			return redirectToQuestion(courseId, moduleId, testId, questionCount);
		}
	}
	
	private String redirectToQuestion(Long courseId, Long moduleId, Long testId, Long questionIndex) {
		return "redirect:/courses/" + courseId + "/modules/" + moduleId + "/tests/" + testId + "/show/" + questionIndex;
	}
	
	private String redirectToTestResult(Long courseId, Long moduleId, Long testId, Long testResult) {
		return "redirect:/courses/" + courseId + "/modules/" + moduleId + "/tests/" + testId + "/testresult/" + testResult;
	}
}
