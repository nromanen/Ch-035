package com.crsms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.crsms.domain.Question;
import com.crsms.dto.QuestionFormDto;
import com.crsms.service.QuestionService;
import com.crsms.validator.QuestionFormValidator;

/**
 * @author Adriets Petro
 */

@Controller
@RequestMapping(value = "/private/courses/{courseId}/modules/{moduleId}/tests/{testId}/questions")
public class PrivateQuestionController {
    private static final String QUESTIONS_PAGE = "questions";
    private static final String CREATE_QUESTION_PAGE = "createquestion";

    @Autowired(required = true)
    private QuestionService questionService;
    
    @Autowired
    private QuestionFormValidator questionFormValidator;
    
    @InitBinder(value = "question")
    private void initBinder(WebDataBinder binder) {
		binder.setValidator(questionFormValidator);
    }

    @RequestMapping(value = { "/add" }, method = RequestMethod.GET)
    public String newQuestion(Model model) {
    	QuestionFormDto question = new QuestionFormDto();
        model.addAttribute("question", question);
        return CREATE_QUESTION_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addQuestion(@PathVariable Long courseId, @PathVariable Long moduleId,
                              @PathVariable Long testId, @Validated QuestionFormDto questionDto,
                              BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_QUESTION_PAGE;
        } else {
            questionService.createQuestion(testId, questionDto);
        }
        return redirect(courseId, moduleId, testId);
    }
    
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    @RequestMapping(value = "/add/question-form")
    @ResponseBody
    public Question addQuestionJson(@PathVariable Long courseId, @PathVariable Long moduleId,
    								@PathVariable Long testId,
    								@Validated QuestionFormDto questionDto, BindingResult result) {
    	Question question = new Question();
    	if (!result.hasErrors()) {
        	try {
        		question = questionService.createQuestionFromForm(testId, questionDto);
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
   	
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
    public String getAllQuestionsByTestId(@PathVariable Long testId, Model model) {
        List<Question> questions = questionService.getQuestionsByTestId(testId);
        model.addAttribute("questions", questions);
        return QUESTIONS_PAGE;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteQuestionById(@PathVariable Long courseId, @PathVariable Long moduleId,
                                     @PathVariable Long testId, @PathVariable("id") Long id) {
        questionService.delete(id);
        return redirect(courseId, moduleId, testId);
    }

    /*
     * Method returns path redirection.
     */
    private String redirect(Long courseId, Long moduleId, Long testId) {
        return "redirect:/private/courses/" + courseId 
        		+ "/modules/" + moduleId
        		+ "/tests/" + testId + "/questions/";
    }

}
