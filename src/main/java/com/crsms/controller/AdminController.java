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
						@RequestParam (value = "sortparam", required = false, defaultValue = "email") String sortParam,
						@RequestParam (value = "direction", required = false, defaultValue = "asc") String direction,
						@RequestParam (value = "keyWord",required = false, defaultValue = "")String keyWord,
						HttpSession session, ModelMap model) {
		
		if (session.getAttribute("direction") == null) {
			session.setAttribute("direction", direction);
		}

		String order = (String) session.getAttribute("direction");
		if (session.getAttribute("sortparam") != null) {
			order = direction;
			session.setAttribute("direction", order);
		} 
		
		String sortingField = (String) session.getAttribute("sortparam");
		if (sortingField == null) {
			session.setAttribute("sortparam", sortParam);
			sortingField = (String) session.getAttribute("sortparam");
		} else {
			session.setAttribute("sortparam", sortParam);
			sortingField = (String) session.getAttribute("sortparam");
		}
		
		int startPosition = (page - 1) * ITEMSPERPAGE;
		List<User> users = userService.getPagingUsers(
				startPosition, ITEMSPERPAGE, sortingField, order, keyWord);
		
		long rowsCount = userService.getRowsCount();
		int lastpage = (int) ((rowsCount / ITEMSPERPAGE));
		
		if (rowsCount > (lastpage * ITEMSPERPAGE)) {
			lastpage++;
		}
		model.addAttribute("lastpage", lastpage);
		model.addAttribute("page", page);
		model.addAttribute("users", users);
		model.addAttribute("keyWord", keyWord);
		return "admin";
	}
	
	@RequestMapping(value = { "/{userId}/delete" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable long userId) {
		User user = userService.getById(userId);
		userService.delete(user);
		return "redirect:/admin/";
	}
	
	@RequestMapping(value = { "/{userId}/edit" }, method = RequestMethod.GET)
	public String editUser(@PathVariable Long userId, ModelMap model) {
		User user = userService.getById(userId);
		model.addAttribute("user", user);
		return "adduser";
	}

	@RequestMapping(value = "/{userId}/edit", method = RequestMethod.POST)
	public String updateUser(@PathVariable long userId, 
								@Validated User user, BindingResult result) {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "adduser";
		}
		userService.update(user);
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
