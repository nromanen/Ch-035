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
import com.crsms.service.CourseService;
import com.crsms.validator.AreaValidator;

/**
 * @author Yuri Kucheriavy
 */
@Controller
@RequestMapping(value = "/areas")
public class AreaConrtoller {

    @Autowired
    private AreaService areaService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private AreaValidator validator;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String getAllAreas(Model model) {
        model.addAttribute("area", new Area());
        model.addAttribute("getAllAreas", areaService.getAll());
        return "area";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showArea(@ModelAttribute("area") Area area,
                           BindingResult result, Model model) {
        return "reenter";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addArea(@ModelAttribute("area") Area area,
                          BindingResult result, Model model) {
        validator.validate(area, result);
        if (result.hasErrors()) {
            model.addAttribute("errors", "result");
            return "reenter";
        }
        if (area.getId() == null) {
            areaService.save(area);
        } else {
            areaService.update(area);
        }
        return "redirect:/areas/";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editPreparing(@PathVariable("id") Long id, Model model) {
        model.addAttribute("area", areaService.getById(id));
        model.addAttribute("getAllAreas", areaService.getAll());
        return "area";
    }

    @RequestMapping("/{id}/delete")
    public String deleteAreaById(@PathVariable("id") Long id) {
        areaService.deleteById(id);
        return "redirect:/areas/";
    }

    @RequestMapping("/{id}/courses")
    public String getAreaCourses(@PathVariable("id") Long id) {
        courseService.getAllByAreaId(id);
        return "redirect:/";
    }
}