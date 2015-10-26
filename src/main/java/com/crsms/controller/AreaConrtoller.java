package com.crsms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crsms.domain.Area;
import com.crsms.service.AreaService;
import com.crsms.validator.AreaValidator;

/**
 * @author Yuri Kucheriavy
 *
 */
@Controller
public class AreaConrtoller {
	
	@Autowired
	private AreaService areaService;
	@Autowired
	AreaValidator validator;
	
	@RequestMapping(value = {"/areas"}, method = RequestMethod.GET)
	
	public String getAllAreas(Model model) {
        model.addAttribute("area", new Area());
        model.addAttribute("getAllAreas", areaService.getAllAreas());
        return "area";
    }
	
	@RequestMapping(value = "/areas/add", method = RequestMethod.POST)
    public String addArea(@ModelAttribute("area") Area area, BindingResult result) {
		//Validation code
	    validator.validate(area, result);
	     
	    //Check validation errors
	    if (result.hasErrors()) {
	        return "redirect:/areas";
	    }
	    if(area.getId() == null) {
	    	areaService.saveArea(area);
	    } else {
	    	areaService.updateArea(area);
	    }
        return "redirect:/areas";
    }

	@RequestMapping(value = "/areas/{id}/edit", method = RequestMethod.GET)
	public String editPreparing(@PathVariable("id") Long id, Model model) {
		Area area = areaService.getAreaById(id);
		model.addAttribute("area", area);
        model.addAttribute("getAllAreas", areaService.getAllAreas());
        return "area";
	}

    @RequestMapping("/areas/{id}/delete")
    public String deleteAreaById(@PathVariable("id") Long id) {
        areaService.deleteArea(id);
        return "redirect:/areas";
    }


}
