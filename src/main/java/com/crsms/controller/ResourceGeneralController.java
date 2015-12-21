package com.crsms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crsms.domain.FileBucket;
import com.crsms.domain.Resource;
import com.crsms.service.OptionService;
import com.crsms.service.GoogleDriveService;
import com.crsms.service.ModuleService;
import com.crsms.service.FileService;
import com.crsms.service.ResourceService;
import com.crsms.validator.MultipartFileValidator;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Controller
public class ResourceGeneralController {
	
	private static final String PRIVATE_MODULE_CONTEXT_RESOURCE_PATH 
		= "/private/courses/{courseId}/modules/{moduleId}/resources";
	private static final String PRIVATE_RESOURCE_PATH = "/private/resources";
	private static final String RESOURCE_PATH = "/resources";
	private static final String MODULE_PATH_NAME = "modules";
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private GoogleDriveService googleDriveService;
	
	@Autowired
	private OptionService optionService;
	
	@Autowired
    private MultipartFileValidator multuipartFileValidator;
 
    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(multuipartFileValidator);
    }
	
	private void addAttributesToSaveResource(Model model) {
		Resource resource = new Resource();
		model.addAttribute("resource", resource);
		addResourceTypeAttributes(model);
		addResourceStorageTypeAttributes(model);
		model.addAttribute("fileBucket", new FileBucket());
	}

	private void addResourceStorageTypeAttributes(Model model) {
		model.addAttribute("resourceStorageTypeDB", Resource.StorageType.DB);
		model.addAttribute("resourceStorageTypeCatalina", Resource.StorageType.CATALINA);
		model.addAttribute("resourceStorageTypeGoogleDrive", Resource.StorageType.GOOGLE_DRIVE);
	}

	private void addResourceTypeAttributes(Model model) {
		model.addAttribute("resourceTypeEmbedded", Resource.Type.EMBEDDED);
		model.addAttribute("resourceTypeFile", Resource.Type.FILE);
	}
	
	@RequestMapping(value = { PRIVATE_RESOURCE_PATH + "/", PRIVATE_RESOURCE_PATH + "/all" }, 
			method = RequestMethod.GET)
	public String showAllResources(Model model) {
		List<Resource> resources = resourceService.getAll();
		model.addAttribute("resources", resources);
		model.addAttribute("resource", new Resource());
		return "resources";
	}
	
	@RequestMapping(value = { PRIVATE_MODULE_CONTEXT_RESOURCE_PATH + "/", 
			PRIVATE_MODULE_CONTEXT_RESOURCE_PATH + "/all" }, method = RequestMethod.GET)
	public String showAllModuleResources(@PathVariable() Long moduleId, Model model) {
		List<Resource> resources = resourceService.getAllByModuleId(moduleId);
		model.addAttribute("resources", resources);
		model.addAttribute("resource", new Resource());
		return "resources";
	}
	
	@RequestMapping(value = {PRIVATE_RESOURCE_PATH + "/add", 
			PRIVATE_MODULE_CONTEXT_RESOURCE_PATH + "/add"}, method = RequestMethod.GET)
	public String showResourceForm(Model model, HttpServletRequest request) {
		model.addAttribute("moduleContextPath", 
				request.getRequestURL().indexOf(MODULE_PATH_NAME) > -1 ? true : false);
		model.addAttribute("count", 5);
		model.addAttribute("limit", 5);
		addAttributesToSaveResource(model);
		return "addResource";
	}
	
	@RequestMapping(value = {PRIVATE_RESOURCE_PATH + "/addembedded"}, method = RequestMethod.POST)
	public String saveEmbeddedResource(Resource resource, Model model) {
		resourceService.save(resource);
		return "redirect:" + PRIVATE_RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = {PRIVATE_MODULE_CONTEXT_RESOURCE_PATH + "/addembedded"}, method = RequestMethod.POST)
	public String saveModuleEmbeddedResource(@PathVariable Long moduleId, Resource resource, Model model) {
		moduleService.addResource(moduleId, resource);
		return "redirect:" + PRIVATE_MODULE_CONTEXT_RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = PRIVATE_RESOURCE_PATH + "/uploadfile", method = RequestMethod.POST)
	public String saveFileResource(@Validated FileBucket fileBucket, 
			BindingResult result, Model model) throws IOException {
		if (result.hasErrors()) {
			throw new IOException("FileValidationException");
        }
		resourceService.save(resourceService.uploadRecivedFileAndPrepareResource(fileBucket));		
        return "redirect:" + PRIVATE_RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = PRIVATE_MODULE_CONTEXT_RESOURCE_PATH + "/uploadfile", method = RequestMethod.POST)
    public String saveModuleFileResource(@PathVariable Long moduleId, 
    		@Validated FileBucket fileBucket, BindingResult result, Model model) throws IOException {
		if (result.hasErrors()) {
			throw new IOException("FileValidationException");
        }	
        moduleService.addResource(moduleId, resourceService.uploadRecivedFileAndPrepareResource(fileBucket));		
		return "redirect:" + PRIVATE_MODULE_CONTEXT_RESOURCE_PATH + "/all";
    }
	
	@RequestMapping(value = RESOURCE_PATH + "/downloadfile", method = RequestMethod.GET)
    public void getFileResource(@RequestParam("id") Long fileId,
    			HttpServletResponse response) throws IOException {
		Resource resource = resourceService.getById(fileId);
		fileService.prepareFileAttachmentResponse(resource.getPath(), resource.getStorageType(), response);
    }
	
	@RequestMapping(value = {PRIVATE_RESOURCE_PATH + "/{id}/edit"}, 
			method = RequestMethod.POST)
	public String editResource(@PathVariable Long id, Resource resource, Model model) {
		resourceService.update(resource);
		return "redirect:" + PRIVATE_RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = {PRIVATE_MODULE_CONTEXT_RESOURCE_PATH + "/{id}/edit"}, 
			method = RequestMethod.POST)
	public String editModuleResource(@PathVariable Long id, Resource resource, Model model) {
		resourceService.update(resource);
		return "redirect:" + PRIVATE_MODULE_CONTEXT_RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = {PRIVATE_RESOURCE_PATH + "/{id}/delete"}, method = RequestMethod.GET)
	public String deleteResource(@PathVariable Long id, Model model) {
		model.addAttribute("errorPermission", true);
		return "redirect:" + PRIVATE_RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = {PRIVATE_MODULE_CONTEXT_RESOURCE_PATH + "/{id}/delete"}, method = RequestMethod.GET)
	public String deleteModuleResource(@PathVariable Long id, @PathVariable Long moduleId, Model model) {
		resourceService.delete(id, moduleId);
		return "redirect:" + PRIVATE_MODULE_CONTEXT_RESOURCE_PATH + "/all";
	}
	
}
