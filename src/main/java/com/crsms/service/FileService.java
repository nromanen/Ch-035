package com.crsms.service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	void uploadFile(MultipartFile multipartFile) throws IOException;
	
	File getFileForDownload(String fileName) throws IOException;
	
	String getStoragePath();

}
