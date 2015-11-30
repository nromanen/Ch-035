package com.crsms.service;

import java.io.IOException;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public interface GoogleDriveService {
	
	 Credential authorize() throws IOException;
	 
	 Drive getDriveAPIClientService() throws IOException;
	 
	 File uploadToDrive(java.io.File file) throws IOException;

}
