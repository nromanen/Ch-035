package com.crsms.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crsms.domain.User;
import com.crsms.service.UserService;
@Controller
@RequestMapping("/admin")
public class AdminController {
	private static Logger log = LogManager
			.getLogger(AdminController.class);

	@Autowired
    private UserService service;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String admin (Model model){
		
		model.addAttribute("user", new User());
		model.addAttribute("userlist",service.getAllUsers());
		return "admin";
	}
	@RequestMapping(value= "/user/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("user") User user){
        this.service.saveUser(user);
         
        return "redirect:/users";
         
    }
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getAll() {
      return service.getAllUsers();
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") long id) {
       return service.getUserById(id);
    }
	
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	    public String delete(@PathVariable("id") long id) {
	        service.delete(id);
			return "redirect:/users";
	        
	    }
	 @RequestMapping( method = RequestMethod.PUT)
	    public void update( User user) {
	        service.updateUser(user);
	    }

	    @RequestMapping(value = "/by", method = RequestMethod.GET)
	    public User getByMail(@RequestParam("email") String email) {
	        return service.getUserByEmail(email);
	    }
}
