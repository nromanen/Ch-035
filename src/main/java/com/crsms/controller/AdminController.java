package com.crsms.controller;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crsms.domain.Role;
import com.crsms.domain.User;
import com.crsms.service.RoleService;
import com.crsms.service.UserService;
import com.crsms.validator.AdminValidator;
/**
 * 
 * @author Roman Romaniuk
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	public static final int ITEMSPERPAGE = 4;
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AdminValidator validator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllUsers(
						@RequestParam (value = "page", required = false, defaultValue = "1") int page,
						@RequestParam (value = "sortby", required = false, defaultValue = "email") String sortBy,
						@RequestParam (value = "direction", required = false, defaultValue = "asc") String direction,
						HttpSession session, ModelMap model) {
		
		if (session.getAttribute("ordertype") == null) {
			session.setAttribute("ordertype", direction);
		}
//		
		String orderType = (String) session.getAttribute("ordertype");
		System.out.println("ordertype before setattribute: " + orderType);
		if (session.getAttribute("columnsorting") != null && 
				session.getAttribute("columnsorting").toString().equals(sortBy)) {
			orderType = direction;
			session.setAttribute("ordertype", orderType);
			System.out.println("ordertype after setattribute: " + session.getAttribute("ordertype"));
		}
		
//		
//		 if (session.getAttribute("orderType") != null && ) {
//	        	orderType = Sorting.DESC;
//	        } else if (session.getAttribute("orderType") != null) {
//	        	orderType = Sorting.ASC;
//	        } 
//	        
//		
//		
		
		
		String sortingField = (String) session.getAttribute("columnsorting");
		System.out.println("sortingField start: " + sortingField);
		if (sortingField == null) {
			session.setAttribute("columnsorting", sortBy);
			System.out.println("sortingField set if null: " + sortingField);
			sortingField = (String) session.getAttribute("columnsorting");
		} else {
			session.setAttribute("columnsorting", sortBy);
			System.out.println("sortingField set if not null: " + sortingField);
			sortingField = (String) session.getAttribute("columnsorting");
		}
		
		long rowsCount = userService.getRowsCount();
		int lastpage = (int)((rowsCount/ITEMSPERPAGE));
		if(rowsCount > (lastpage * ITEMSPERPAGE))
		{
			lastpage++;
		}
		int startPosition = (page - 1) * ITEMSPERPAGE;
		
		System.out.println("startposition in getPagingUsers: " + startPosition);
		System.out.println("sortingField in getPagingUsers: " + sortingField);
		System.out.println("order in getPagingUsers: " + orderType);

		List<User> users = userService.getPagingUsers(startPosition, ITEMSPERPAGE, sortingField, orderType);
		model.addAttribute("lastpage", lastpage);
		model.addAttribute("page", page);
		model.addAttribute("users", users);
		return "admin";
		
		
	}
	
	@RequestMapping(value = { "/delete/{userId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable long userId) {
		userService.delete(userId);
		return "redirect:/admin/";
	}
	
	@RequestMapping(value = { "/adduser" }, method = RequestMethod.GET)
	public String addUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "adduser";
	}
	
	@RequestMapping(value = { "/adduser" }, method = RequestMethod.POST)
	public String saveUser(@Validated User user, BindingResult result,
			ModelMap model) {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "adduser";
		}
		userService.saveUser(user);
		return "redirect:/admin/";
	}

	@RequestMapping(value = { "/edit/{userId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable Long userId, ModelMap model) {
		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "adduser";
	}

	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
	public String updateUser(@PathVariable long userId, 
								@Validated User user, BindingResult result) {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "adduser";
		}
		userService.saveUser(user);
		return "redirect:/admin/";
	}
	
	@ModelAttribute("roles")
	public List<Role> initializeRoles() {
		List<Role> roles = new ArrayList<>();
		roles = roleService.getAllRoles();
		return roles;
	}

	class RoleEditor extends PropertyEditorSupport {
		@Override
        public void setAsText(String text) {
            Long id = Long.parseLong(text);
            Role role = roleService.getRoleById(id);
            setValue(role);
        }
    }
 
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, new RoleEditor());
    }
}
