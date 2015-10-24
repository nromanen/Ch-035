package com.crsms.controller;

import com.crsms.domain.Test;
import com.crsms.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Adriets Petro
 */

@Controller
@RequestMapping(value = "courses/{courseId}/modules/{moduleId}/tests")
public class TestController {
    @Autowired(required = true)
    private TestService testService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTest(@PathVariable Long courseId, @PathVariable Long moduleId, @ModelAttribute("test") Test test) {
        testService.createTest(test);
        return redirect(courseId, moduleId);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateTest(@PathVariable Long courseId, @PathVariable Long moduleId, @ModelAttribute("test") Test test) {
        testService.editTest(test);
        return redirect(courseId, moduleId);
    }

    @RequestMapping(value = "{id}/edit")
    public String updateTestByID(@PathVariable("id") Long id, Model model) {
        Test tempTest = testService.getTestById(id);
        testService.editTest(tempTest);
        model.addAttribute("test", tempTest);
        model.addAttribute("tests", testService.getAllTests());
        return "test";
    }

    @RequestMapping(value = "{id}/tests", method = RequestMethod.GET)
    public String getTestById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("test", new Test());
        model.addAttribute("getTestById", testService.getTestById(id));
        return "test";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllTests(Model model) {
        model.addAttribute("test", new Test());
        model.addAttribute("tests", testService.getAllTests());
        return "test";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String deleteTest(@PathVariable Long courseId, @PathVariable Long moduleId, @ModelAttribute("test") Test test) {
        testService.deleteTest(test);
        return redirect(courseId, moduleId);
    }

    @RequestMapping("{id}/remove")
    public String deleteTestById(@PathVariable Long courseId, @PathVariable Long moduleId, @PathVariable("id") Long id) {
        testService.deleteTestById(id);
        return redirect(courseId, moduleId);
    }

    /*
     * Method returns redirection path.
     */
    private String redirect(Long courseId, Long moduleId) {
        return "redirect:/courses/" + courseId + "/modules/" + moduleId + "/tests";
    }

}
