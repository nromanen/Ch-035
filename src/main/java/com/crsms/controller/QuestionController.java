package com.crsms.controller;

import com.crsms.domain.Question;
import com.crsms.service.QuestionService;
import com.crsms.validator.QuestionFormValidator;

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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * @author Adriets Petro
 */

@Controller
@RequestMapping(value = "/courses/{courseId}/modules/{moduleId}/tests/{testId}/questions")
public class QuestionController {
    private final String QUESTIONS_PAGE = "questions";
    public final String CREATE_QUESTION_PAGE = "createquestion";

    @Autowired(required = true)
    private QuestionService questionService;
    
    @Autowired
    private QuestionFormValidator questionFormValidator;
    
    public QuestionController() {}

    @RequestMapping(value = { "/add" }, method = RequestMethod.GET)
    public String newQuestion(Model model) {
        Question question = new Question();
        model.addAttribute("question", question);
        return CREATE_QUESTION_PAGE;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addQuestion(@PathVariable Long courseId, @PathVariable Long moduleId,
                              @PathVariable Long testId, @Validated Question question,
                              BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_QUESTION_PAGE;
        } else {
            questionService.createQuestion(testId, question);
        }
        return redirect(courseId, moduleId, testId);
    }
    
    //Need to return QuestionFormDto?
    @RequestMapping(value = "/add/question-form")
    public @ResponseBody Question addQuestionJson(@PathVariable Long courseId, @PathVariable Long moduleId,
                         @PathVariable Long testId, @Validated Question question, BindingResult result) {
        if (!result.hasErrors()) {
        	questionService.createQuestion(testId, question);
        } else {
        	throw new IllegalArgumentException("QuestionController. AJAX pocessing error.");
        }
        return question;
    }
    
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editQuestion(@PathVariable("id") Long id, Model model) {
        Question tempQuestion = questionService.getById(id);
        model.addAttribute("question", tempQuestion);
        return CREATE_QUESTION_PAGE;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String editQuestion(@PathVariable Long courseId, @PathVariable Long moduleId,
                               @PathVariable Long testId, @PathVariable Long id,
                               @Validated Question question, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_QUESTION_PAGE;
        } else if (questionService.getById(id) != null) {
            questionService.update(question);
        }
        return redirect(courseId, moduleId, testId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllQuestionsByTestId (@PathVariable Long testId, Model model) {
        List<Question> questions = questionService.getQuestionsByTestId(testId);
        model.addAttribute("questions", questions);
        return QUESTIONS_PAGE;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteQuestionById (@PathVariable Long courseId, @PathVariable Long moduleId,
                                      @PathVariable Long testId, @PathVariable("id") Long id) {
        questionService.disable(id);
        return redirect(courseId, moduleId, testId);
    }

    /*
     * Method returns path redirection.
     */
    private String redirect(Long courseId, Long moduleId, Long testId) {
        return "redirect:/courses/" + courseId + "/modules/" + moduleId + "/tests/" + testId + "/questions/";
    }
    
	
	/*
	 * Method for form validation binding.
	 */
    @InitBinder(value="question")
    private void initBinder(WebDataBinder binder) {
		binder.setValidator(questionFormValidator);
    }

}
