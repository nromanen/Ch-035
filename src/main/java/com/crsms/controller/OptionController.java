package com.crsms.controller;

import java.util.Arrays;
import java.util.Properties;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crsms.domain.Option;
import com.crsms.domain.Resource;
import com.crsms.service.OptionService;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Controller
@RequestMapping(value = "/private/options")
public class OptionController {
	
	@Autowired
	OptionService optionService;
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String getOptions(Model model) {
		Properties options = optionService.getOptionsAsProperties();
		model.addAttribute("options", options);
		model.addAttribute("storageTypeOptionCurrent", options.getProperty(Option.STORAGE_TYPE_OPTION_KEY));
		model.addAttribute("storageTypeOptionKey", Option.STORAGE_TYPE_OPTION_KEY);
		model.addAttribute("storageTypes", Arrays.copyOfRange(Resource.StorageType.values(), 1, Resource.StorageType.values().length));
		return "options";
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.POST)
	public String saveOptions(ServletRequest request) {
		Option option = new Option();
		option.setKey(Option.STORAGE_TYPE_OPTION_KEY);
		option.setValue(request.getParameter(Option.STORAGE_TYPE_OPTION_KEY));
		optionService.update(option);
		return "redirect:/private/options/";
	}
	
}
