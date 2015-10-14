package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Test MVC");
		return "hello";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String calcSumm(Model model,
						   @RequestParam(value = "value1") Double value1,
						   @RequestParam(value = "value2") Double value2){
		if (value1 == null || value2 == null) {
			return "illegal"; 
		} else {
			model.addAttribute("result", (value1 + value2));
			return "calculated";
		}
		

	}
}