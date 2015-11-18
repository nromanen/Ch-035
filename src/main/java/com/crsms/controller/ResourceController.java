package com.crsms.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crsms.domain.FileBucket;
import com.crsms.domain.Resource;
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
public class ResourceController {
	
	private static final String MODULE_CONTEXT_RESOURCE_PATH 
		= "/courses/{courseId}/modules/{moduleId}/resources";
	private static final String RESOURCE_PATH = "/resources";
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
    private MultipartFileValidator multuipartFileValidator;
 
    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(multuipartFileValidator);
    }
	
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
	public String saveFileResource(@Validated FileBucket fileBucket, 
			BindingResult result, Model model) throws IOException {
		if (result.hasErrors()) {
			throw new IOException("FileValidationException");
        }
		MultipartFile receivedFile = fileBucket.getFile();
		String originalName = receivedFile.getOriginalFilename();		
		fileService.uploadFile(receivedFile);		
		resourceService.save(originalName, fileService.getStoragePath());		
        return "redirect:" + RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = MODULE_CONTEXT_RESOURCE_PATH + "/addfile", method = RequestMethod.POST)
    public String saveModuleFileResource(@PathVariable Long moduleId, 
    		@Validated FileBucket fileBucket, BindingResult result, Model model) throws IOException {
		if (result.hasErrors()) {
			throw new IOException("FileValidationException");
        }
		MultipartFile receivedFile = fileBucket.getFile();
		String originalName = receivedFile.getOriginalFilename();		
		fileService.uploadFile(receivedFile);		
        moduleService.addResource(moduleId, originalName, fileService.getStoragePath());		
		return "redirect:" + MODULE_CONTEXT_RESOURCE_PATH + "/all";
    }
	
	@RequestMapping(value = RESOURCE_PATH + "/downloadfile", method = RequestMethod.GET)
    public void getFileResource(@RequestParam("filename") String fileName,
    			HttpServletRequest request,
    			HttpServletResponse response) throws IOException {
		File file = fileService.getFileForDownload(fileName);
		// set content attributes for the response
		String mimeType = request.getServletContext().getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
		response.setContentLength((int) file.length());
		// set headers for the response
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
		// get file as InputStream
		InputStream in = new FileInputStream(file);
		// copy it to response's OutputStream
		FileCopyUtils.copy(in, response.getOutputStream());
		response.flushBuffer();
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
	public String deleteResource(@PathVariable Long id, Model model) {
		model.addAttribute("errorPermission", true);
		return "redirect:" + RESOURCE_PATH + "/all";
	}
	
	@RequestMapping(value = {MODULE_CONTEXT_RESOURCE_PATH + "/{id}/delete"}, method = RequestMethod.GET)
	public String deleteModuleResource(@PathVariable Long id, @PathVariable Long moduleId, Model model) {
		resourceService.delete(id, moduleId);
		return "redirect:" + MODULE_CONTEXT_RESOURCE_PATH + "/all";
	}
	
	
}
