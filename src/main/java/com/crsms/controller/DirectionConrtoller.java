package com.crsms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crsms.domain.Direction;
import com.crsms.service.DirectionService;

/**
 * @author Yuri Kucheriavy
 *
 */
@Controller
public class DirectionConrtoller {
	
	@Autowired
	private DirectionService directionService;
	
	@RequestMapping(value = {"/directions"}, method = RequestMethod.GET)
	
	public String getAllDirections(Model model) {
        model.addAttribute("direction", new Direction());
        model.addAttribute("getAllDirections", directionService.getAllDirections());
        return "direction";
    }
	
	@RequestMapping(value = "/direction/add", method = RequestMethod.POST)
    public String addDirection(@ModelAttribute("direction") Direction direction) {
        directionService.saveDirection(direction);
        return "redirect:/directions";
    }

    @RequestMapping(value = "/direction/edit", method = RequestMethod.POST)
    public String updateDirection(@ModelAttribute("direction") Direction direction) {
        directionService.updateDirection(direction);
        return "redirect:/directions";
    }

    @RequestMapping(value = "/direction/{id}", method = RequestMethod.GET)
    public String getDirectionById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("direction", new Direction());
        model.addAttribute("getDirectionById", directionService.getDirectionById(id));
        return "direction";
    }

    @RequestMapping("/direction/remove/{id}")
    public String deleteDirectionById(@PathVariable("id") Long id) {
        directionService.deleteDirection(id);
        return "redirect:/directions";
    }


}
