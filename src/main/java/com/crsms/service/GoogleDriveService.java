package com.crsms.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public interface GoogleDriveService {
	
	 Credential authorize() throws IOException;
	 
	 Drive getDriveAPIClientService() throws IOException;
	 
	 File uploadToDrive(MultipartFile multipartFile) throws IOException;

}
