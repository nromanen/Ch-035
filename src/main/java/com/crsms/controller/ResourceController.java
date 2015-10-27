package com.crsms.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import com.crsms.domain.FileBucket;
import com.crsms.domain.Resource;
import com.crsms.service.ResourceService;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Controller
@RequestMapping(value = {"/course/{courseId}/module/{moduleId}/resources",
		"/resources"})
public class ResourceController {
	
	private final String STORAGE_PATH = "storage/resources";
	private final String DELETE_PATH_PATTERN = "\\/\\d+\\/delete$";
	
	@Autowired
	private ResourceService resourceService;
	
	// returns relative mapping path to controller by removing method mapping part
	private String getControllerClassPath(String link, String methodPattern) {
		return link.replaceFirst(methodPattern, "");
	}
	
	private void addAttributesForSaveResource(Model model) {
		Resource resource = new Resource();
		model.addAttribute("resource", resource);
		model.addAttribute("resourceTypeEmbedded", Resource.Type.EMBEDDED);
		model.addAttribute("resourceTypeFile", Resource.Type.FILE);
		model.addAttribute("fileBucket", new FileBucket());
	}
	
	@RequestMapping(value = { "/", "/all" }, method = RequestMethod.GET)
	public String showAllResources(Model model) {
		List<Resource> resources = resourceService.getAll();
		model.addAttribute("resources", resources);
		return "resources";
	}
	
	@RequestMapping(value = {"/add"}, method = RequestMethod.GET)
	public String showResourceForm(Model model) {
		addAttributesForSaveResource(model);
		return "addResource";
	}
	
	@RequestMapping(value = {"/addembedded"}, method = RequestMethod.POST)
	public String saveEmbeddedResource(Resource resource, Model model) {
		resourceService.save(resource);
		addAttributesForSaveResource(model);
		model.addAttribute("success", true);
		return "addResource";
	}
	
	@RequestMapping(value = "/addfile", method = RequestMethod.POST)
    public String saveFileResource(FileBucket fileBucket, Model model) {
 
		MultipartFile receivedFile = fileBucket.getFile();
		String originalName = receivedFile.getOriginalFilename();
		
		// TODO add this condition and some others to validator
		if (!receivedFile.isEmpty()) {
        	
        	try {
                // Creating if not exist the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + STORAGE_PATH);
                if (!dir.exists())
                    dir.mkdirs();
                String filePath = dir.getAbsoluteFile() + File.separator + originalName;
                
                try {
                	receivedFile.transferTo(new File(filePath));
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    return "File uploaded failed:" + originalName;
                } catch (IOException e) {
                    e.printStackTrace();
                    return "File uploaded failed:" + originalName;
                }
                
                Resource resource = new Resource();
                resource.setName(originalName);
                resource.setType(Resource.Type.FILE);
                resource.setUrl(filePath);
                resourceService.save(resource);
        		addAttributesForSaveResource(model);
        		model.addAttribute("success", true);
        		return "addResource";


            } catch (Exception e) {
                return "You failed to upload " + originalName + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + originalName
                    + " because the file was empty.";
        }
    }
	
	@RequestMapping(value = {"/{id}/edit"}, method = RequestMethod.GET)
	public String showEditResourceForm(@PathVariable Long id, Model model) {
		Resource resource = resourceService.getById(id);
		model.addAttribute("resource", resource);
		model.addAttribute("resourceTypeValues", Resource.Type.values());
		FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
		return "fileUpload";
	}
	
	@RequestMapping(value = {"/{id}/delete"}, method = RequestMethod.GET)
	public String deleteResource(@PathVariable Long id, HttpServletRequest request, Model model) {
		resourceService.deleteById(id);
		return "redirect:" + 
			this.getControllerClassPath(request.getServletPath(), DELETE_PATH_PATTERN) + "/all";
	}
	
	
	
}
