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
import com.crsms.domain.TeacherRequest;
import com.crsms.domain.User;
import com.crsms.service.RoleService;
import com.crsms.service.TeacherRequestService;
import com.crsms.service.UserService;
import com.crsms.validator.AdminValidator;
/**
 * 
 * @author Roman Romaniuk
 *
 */
@Controller
@RequestMapping(value = "/private/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AdminValidator validator;
	
	@Autowired
	private TeacherRequestService requestService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllUsers(
						@RequestParam (value = "page", required = false, defaultValue = "1") int page,
						@RequestParam (value = "sortparam", required = false, defaultValue = "email") String sortParam,
						@RequestParam (value = "direction", required = false, defaultValue = "asc") String direction,
						@RequestParam (value = "keyWord",required = false, defaultValue = "")String keyWord,
						@RequestParam (value = "itemsperpage", required = false, defaultValue = "6") int itemsPerPage,
						HttpSession session, ModelMap model) {
		
		if (session.getAttribute("direction") == null) 
			session.setAttribute("direction", direction);

		int offSet = (page - 1) * itemsPerPage;
		long rowsCount = userService.getRowsCount(keyWord);
		String order = (String) session.getAttribute("direction");
		String sortingField = (String) session.getAttribute("sortparam");
		int lastpage = (int) ((rowsCount / itemsPerPage));
		
		if (rowsCount > (lastpage * itemsPerPage)) 
			lastpage++;
		
		if (session.getAttribute("direction") != null) {
			order = direction;
			session.setAttribute("direction", order);
		} 
		
		if (sortingField == null) {
			session.setAttribute("sortparam", sortParam);
			sortingField = (String) session.getAttribute("sortparam");
		} else {
			session.setAttribute("sortparam", sortParam);
			sortingField = (String) session.getAttribute("sortparam");
		}
		List<TeacherRequest> requests = requestService.getRequestsHistory();
		List<User> usersToApprove = userService.getUsersToApprove();
		model.addAttribute("lastpage", lastpage);
		model.addAttribute("page", page);
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("itemsperpage", itemsPerPage);
		model.addAttribute("usersToApprove", usersToApprove);
		model.addAttribute("requests", requests);
		model.addAttribute("rowscount", rowsCount);
		model.addAttribute("usersToApproveCount", 
						userService.getUsersToApproveCount());
		model.addAttribute("teacherRequestsCount", 
						requestService.getRequestsHistoryCount());
		model.addAttribute("users",  userService.getPagingUsers(
						offSet, itemsPerPage, sortingField, order, keyWord));
		return "admin";
	}
	
	@RequestMapping(value = { "/{userId}/delete" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable long userId) {
		User user = userService.getById(userId);
		userService.delete(user);
		return "redirect:/private/admin/";
	}
	
	@RequestMapping(value = { "/{userId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable Long userId, ModelMap model) {
		User user = userService.getById(userId);
		model.addAttribute("user", user);
		return "edituser";
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	public String updateUser(@PathVariable long userId,@Validated User user, BindingResult result) {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "edituser";
		}
		userService.update(user);
		return "redirect:/private/admin/";
	}

	@RequestMapping(value = { "/request/{requestId}" }, method = RequestMethod.GET)
	public String editRequest(@PathVariable Long requestId, ModelMap model) {
		TeacherRequest request = requestService.getById(requestId);
		model.addAttribute("request", request);
		return "request";
	}
	
	@RequestMapping(value = "/request/{requestId}", method = RequestMethod.POST)
	public String updateRequest(@PathVariable long requestId, 
			@RequestParam(value = "approve", required = false) boolean approve){
		requestService.setApprovedStatus(requestId, approve);
		return "redirect:/private/admin/#";
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
