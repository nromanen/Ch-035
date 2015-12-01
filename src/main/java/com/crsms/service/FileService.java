package com.crsms.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import com.crsms.domain.Resource;
import com.crsms.domain.Resource.StorageType;

/**
 * 
 * @author Valerii Motresku
 *
 */

public interface FileService {
	
	public static final String ROOT_PATH = System.getProperty("catalina.home");
	public static final String STORAGE_PATH =  ROOT_PATH + File.separator + "storage";
	public static final String RESOURCE_PATH = STORAGE_PATH + File.separator + "resources";

	Resource.StorageType getResourceStorageTypeOption();
	
	/**
	 * 
	 * @param multipartFile
	 * @param storageType
	 * @return file path in the particular storage type format
	 * @throws IOException
	 */
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	String uploadFile(MultipartFile multipartFile, Resource.StorageType storageType) throws IOException;
	
	void prepareFileAttachmentResponse(String path, StorageType storageType, 
			HttpServletResponse response) throws IOException;

}
