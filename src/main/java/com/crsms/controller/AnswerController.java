package com.crsms.controller;

import com.crsms.domain.Answer;
import com.crsms.service.AnswerService;
import com.crsms.validator.AnswerFormValidator;
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
 * @author Adriets Petro
 */

@Controller
@RequestMapping(value = "/courses/{courseId}/modules/{moduleId}/tests/{testId}/questions/questionId")
public class AnswerController {
    private final String ANSWERS_PAGE = "answers";
    public final String CREATE_ANSWER_PAGE = "createanswer";

    @Autowired
    private AnswerService answerService;

    @Autowired
    private AnswerFormValidator formValidator;

    public AnswerController() {}

    @RequestMapping(value = { "/add" }, method = RequestMethod.GET)
    public String newAnswer(Model model) {
        Answer answer = new Answer();
        model.addAttribute("answer", answer);
        return CREATE_ANSWER_PAGE;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAnswer(@PathVariable Long courseId, @PathVariable Long moduleId,
                            @PathVariable Long testId, @PathVariable Long questionId,
                            @Validated Answer answer, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_ANSWER_PAGE;
        } else {
            answerService.createAnswer(questionId, answer);
        }
        return redirect(courseId, moduleId, testId, questionId);
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editAnswer(@PathVariable("id") Long id, Model model) {
        Answer tempAnswer = answerService.getAnswerById(id);
        model.addAttribute("answer", tempAnswer);
        return CREATE_ANSWER_PAGE;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String editAnswer(@PathVariable Long courseId, @PathVariable Long moduleId,
                             @PathVariable Long testId, @PathVariable Long questionId, @PathVariable Long id,
                             @Validated Answer answer, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_ANSWER_PAGE;
        } else if (answerService.getAnswerById(id) != null) {
            answerService.editAnswer(answer);
        }
        return redirect(courseId, moduleId, testId, questionId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllAnswersByQuestionId (@PathVariable Long questionId, Model model) {
        List<Answer> answers = answerService.getAnswersByQuestionId(questionId);
        model.addAttribute("answers", answers);
        return ANSWERS_PAGE;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteQuestionById (@PathVariable Long courseId, @PathVariable Long moduleId,
                                      @PathVariable Long testId, @PathVariable Long questionId,
                                      @PathVariable("id") Long id) {
        answerService.disable(id);
        return redirect(courseId, moduleId, testId, questionId);
    }

    /*
     * Method returns path redirection.
     */
    private String redirect(Long courseId, Long moduleId, Long testId, Long questionId) {
        return "redirect:/courses/" + courseId + "/modules/" + moduleId
                        + "/tests/" + testId + "/questions/" + questionId + "/answers/";
    } 
    
	/*
	 * Method for form validation binding.
	 */
    @InitBinder
    private void intiBinder(WebDataBinder binder) {
        binder.setValidator(formValidator);
    }

}
