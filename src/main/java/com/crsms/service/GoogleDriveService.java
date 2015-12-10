package com.crsms.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.drive.Drive;

/**
 * 
 * @author Valerii Motresku
 *
 */

public interface GoogleDriveService {
	
	 Credential getUserAccountCredential() throws IOException;
	 
	 Drive getDriveAPIClientService() throws IOException;
	 
	 com.google.api.services.drive.model.File uploadToDrive(MultipartFile multipartFile) throws IOException;
	 
	 com.google.api.services.drive.model.File getMediaFileFromDrive(String id) throws IOException;
	 
	 InputStream getMediaStreamFromDrive(String id) throws IOException;

}
