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
public class TestController {
    @Autowired(required = true)
    private TestService testService;

    @RequestMapping(value = "/test/add", method = RequestMethod.POST)
    public String addTest(@ModelAttribute("test") Test test) {
        testService.createTest(test);
        return "redirect:/tests";
    }

    @RequestMapping(value = "/test/edit", method = RequestMethod.POST)
    public String updateTest(@ModelAttribute("test") Test test) {
        testService.editTest(test);
        return "redirect:/tests";
    }

    @RequestMapping(value = "/test/edit/{id}")
    public String updateTestByID(@PathVariable("id") Long id, Model model) {
        model.addAttribute("test", testService.getTestById(id));
        model.addAttribute("getAllTests", testService.getAllTests());
        return "test";
    }

    //TODO test this method.
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTestById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("test", new Test());
        model.addAttribute("getTestById", testService.getTestById(id));
        return "test";
    }

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public String getAllTests(Model model) {
        model.addAttribute("test", new Test());
        model.addAttribute("getAllTests", testService.getAllTests());
        return "test";
    }

    //TODO test this method.
    @RequestMapping(value = "/test/remove", method = RequestMethod.POST)
    public String deleteTest(@ModelAttribute("test") Test test) {
        testService.deleteTest(test);
        return "redirect:/tests";
    }

    @RequestMapping("/test/remove/{id}")
    public String deleteTestById(@PathVariable("id") Long id) {
        testService.deleteTestById(id);
        return "redirect:/tests";
    }

}
