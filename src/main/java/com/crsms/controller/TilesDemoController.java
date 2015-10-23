package com.crsms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TilesDemoController {

	@RequestMapping(value = { "/tiles-demo" }, method = RequestMethod.GET)
	public String welcome(Model model){
        String[] sliderCaptions = new String[3];
        sliderCaptions[0] = "Hello my Friend. Here is the Apache Tiles example.";
        sliderCaptions[1] =" And this is welcome content definition.";
        sliderCaptions[2] = " Peace! Take care of yourself.";
        model.addAttribute("sliderCaptions", sliderCaptions);
        return "tiles-demo-def";
    }
	
	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public String index(Model model){
        return "index";
    }
}