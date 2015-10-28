package com.crsms.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface MultipartFileService {
	
	void uploadFile(MultipartFile multipartFile) throws IOException;
	
	String getStoragePath();

}
