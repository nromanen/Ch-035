package com.crsms.controller;

import java.util.List;

import com.crsms.domain.Module;
import com.crsms.domain.Test;
import com.crsms.service.ModuleService;
import com.crsms.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Adriets Petro, Roman Stefankiv
 */

@Controller
@RequestMapping(value = "/courses/{courseId}/modules/{moduleId}/tests/")
public class TestController {

	@Autowired(required = true)
	private TestService testService;

	@Autowired(required = true)
	private ModuleService moduleService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addTest(@PathVariable Long courseId,
			@PathVariable Long moduleId, @ModelAttribute("test") Test test) {
		testService.createTest(moduleId, test);
		return redirect(courseId, moduleId);
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String newTest(Model model) {
		Test test = new Test();
		List<Module> modules = moduleService.getAll();
		model.addAttribute("test", test);
		model.addAttribute("modules", modules);
		return "createtest";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String updateTest(@PathVariable Long courseId,
			@PathVariable Long moduleId, @PathVariable Long id,
			@ModelAttribute("test") Test test) {
		if (testService.getTestById(id) != null) {
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

	// TODO Need to resolve problem with / forwarding.
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getAllTests(Model model) {
		model.addAttribute("test", new Test());
		model.addAttribute("tests", testService.getAllTests());
		return "tests";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String deleteTestById(@PathVariable Long courseId,
			@PathVariable Long moduleId, @PathVariable("id") Long id) {
		testService.deleteTestById(id);
		return redirect(courseId, moduleId);
	}

/*	// Method from previous controller realization. Don't using yet in JSP pages.
	@RequestMapping(value = "{id}/tests", method = RequestMethod.GET)
	public String getTestById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("test", new Test());
		model.addAttribute("getTestById", testService.getTestById(id));
		return "tests";
	}

	// Method from previous TestController realization. Don't using yet in JSP pages.
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String deleteTest(@PathVariable Long courseId,
			@PathVariable Long moduleId, @ModelAttribute("test") Test test) {
		testService.deleteTest(test);
		return redirect(courseId, moduleId);
	}*/

	/*
	 * Method returns redirection path.
	 */
	private String redirect(Long courseId, Long moduleId) {
		return "redirect:/courses/" + courseId + "/modules/" + moduleId
				+ "/tests/";
	}

}
