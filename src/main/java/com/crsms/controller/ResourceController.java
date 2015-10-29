package com.crsms.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.crsms.domain.FileBucket;
import com.crsms.domain.Resource;
import com.crsms.service.ModuleService;
import com.crsms.service.MultipartFileService;
import com.crsms.service.ResourceService;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Controller
public class ResourceController {
	
	private static final String MODULE_CONTEXT_RESOURCE_PATH 
		= "/courses/{courseId}/modules/{moduleId}/resources";
	private static final String RESOURCE_PATH = "/resources";
	
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private MultipartFileService multipartFileService;

	
	private void addAttributesForSaveResource(Model model) {
		Resource resource = new Resource();
		model.addAttribute("resource", resource);
		model.addAttribute("resourceTypeEmbedded", Resource.Type.EMBEDDED);
		model.addAttribute("resourceTypeFile", Resource.Type.FILE);
		model.addAttribute("fileBucket", new FileBucket());
	}
	
	
	
	@RequestMapping(value = { RESOURCE_PATH + "/", RESOURCE_PATH + "/all" }, 
			method = RequestMethod.GET)
	public String showAllResources(Model model) {
		List<Resource> resources = resourceService.getAll();
		model.addAttribute("resources", resources);
		model.addAttribute("resource", new Resource());
		return "resources";
	}
	
	@RequestMapping(value = { MODULE_CONTEXT_RESOURCE_PATH + "/", 
			MODULE_CONTEXT_RESOURCE_PATH + "/all" }, method = RequestMethod.GET)
	public String showAllModuleResources(@PathVariable() Long moduleId, Model model) {
		List<Resource> resources = resourceService.getAllByModuleId(moduleId);
		model.addAttribute("resources", resources);
		model.addAttribute("resource", new Resource());
		return "resources";
	}
	
	@RequestMapping(value = {RESOURCE_PATH + "/add", 
			MODULE_CONTEXT_RESOURCE_PATH + "/add"}, method = RequestMethod.GET)
	public String showResourceForm(Model model) {
		addAttributesForSaveResource(model);
		return "addResource";
	}
	
	@RequestMapping(value = {RESOURCE_PATH + "/addembedded"}, method = RequestMethod.POST)
	public String saveEmbeddedResource(Resource resource, Model model) {
		resourceService.save(resource);
		return "redirect:" + RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = {MODULE_CONTEXT_RESOURCE_PATH + "/addembedded"}, method = RequestMethod.POST)
	public String saveModuleEmbeddedResource(@PathVariable Long moduleId, Resource resource, Model model) {
		moduleService.addResource(moduleId, resource);
		return "redirect:" + MODULE_CONTEXT_RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = RESOURCE_PATH + "/addfile", method = RequestMethod.POST)
	public String saveFileResource(FileBucket fileBucket, Model model) throws IOException {
		MultipartFile receivedFile = fileBucket.getFile();
		String originalName = receivedFile.getOriginalFilename();		
		multipartFileService.uploadFile(receivedFile);		
		resourceService.save(originalName, multipartFileService.getStoragePath());		
        return "redirect:" + RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = MODULE_CONTEXT_RESOURCE_PATH + "/addfile", method = RequestMethod.POST)
    public String saveModuleFileResource(@PathVariable Long moduleId, FileBucket fileBucket, Model model) throws IOException {
		MultipartFile receivedFile = fileBucket.getFile();
		String originalName = receivedFile.getOriginalFilename();		
		multipartFileService.uploadFile(receivedFile);		
        moduleService.addResource(moduleId, originalName, multipartFileService.getStoragePath());		
		return "redirect:" + MODULE_CONTEXT_RESOURCE_PATH + "/all";
    }
	
	// @ResponseBody to return json in response body
	@RequestMapping(value = {RESOURCE_PATH + "/{id}/json"}, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Resource getJsonResource(@PathVariable Long id) {
		return resourceService.getById(id);
	}
	
	@RequestMapping(value = {RESOURCE_PATH + "/{id}/edit"}, method = RequestMethod.POST)
	public String editResource(@PathVariable Long id, Resource resource, Model model) {
		resourceService.update(resource);
		return "redirect:" + RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = {MODULE_CONTEXT_RESOURCE_PATH + "/{id}/edit"}, method = RequestMethod.POST)
	public String editModuleResource(@PathVariable Long id, Resource resource, Model model) {
		resourceService.update(resource);
		return "redirect:" + MODULE_CONTEXT_RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = {RESOURCE_PATH + "/{id}/delete"}, method = RequestMethod.GET)
	public String deleteResource(@PathVariable Long id, HttpServletRequest request, Model model) {
		resourceService.deleteById(id);
		return "redirect:" + RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = {MODULE_CONTEXT_RESOURCE_PATH + "/{id}/delete"}, method = RequestMethod.GET)
	public String deleteModuleResource(@PathVariable Long id, HttpServletRequest request, Model model) {
		resourceService.deleteById(id);
		return "redirect:" + MODULE_CONTEXT_RESOURCE_PATH + "/all";
	}
	
	
	
}
