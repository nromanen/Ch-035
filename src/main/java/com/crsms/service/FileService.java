package com.crsms.service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.crsms.domain.Resource;

public interface FileService {
	
	Resource.StorageType getResourceStorageTypeOption();
	
	/**
	 * 
	 * @param multipartFile
	 * @return file path in particular storage type format
	 * @throws IOException
	 */
	String uploadFile(MultipartFile multipartFile) throws IOException;
	
	File getFileToDownload(String fileName) throws IOException;

}
