package com.crsms.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crsms.domain.Resource;

@Service("fileService")
public class FileServiceImpl implements FileService {
	
	@Autowired
	private OptionService optionService;
	
	@Autowired
	private GoogleDriveService googleDriveService;
	
	public static final String ROOT_PATH = System.getProperty("catalina.home");
	public static final String STORAGE_PATH =  ROOT_PATH + File.separator + "storage";
	public static final String RESOURCE_PATH = STORAGE_PATH + File.separator + "resources";
	public static final String STORAGE_TYPE_OPTION_KEY = "storage.type";

	private void uploadToCatalinaHome(MultipartFile multipartFile)
			throws IOException {
		String originalName = multipartFile.getOriginalFilename();
        // Creating if not exist the directory to store file
        File dir = new File(RESOURCE_PATH);
        if (!dir.exists())
            dir.mkdirs();
        String filePath = dir.getAbsoluteFile() + File.separator + originalName;
        multipartFile.transferTo(new File(filePath));
	}
	
	private com.google.api.services.drive.model.File uploadToGoogleDrive(MultipartFile multipartFile) 
			throws IOException {
		return googleDriveService.uploadToDrive(multipartFile);
	}
	
	@Override
	public Resource.StorageType getResourceStorageTypeOption() {
		return Resource.StorageType.valueOf(optionService.getOptionsAsProperties().getProperty(STORAGE_TYPE_OPTION_KEY));
	}

	@Override
	public String uploadFile(MultipartFile multipartFile) throws IOException {	
		switch(getResourceStorageTypeOption()) {
			case CATALINA:
				uploadToCatalinaHome(multipartFile);
				return multipartFile.getOriginalFilename();
			case GOOGLE_DRIVE:
				return uploadToGoogleDrive(multipartFile).getId();
			default:
				// by conventions
				throw new FileSystemNotFoundException(
						"There is not suitable method for the " 
						+ getResourceStorageTypeOption() + " storage type");
		}
	}

	@Override
	public File getFileToDownload(String fileName) throws IOException {
		File file = new File(RESOURCE_PATH + File.separator + fileName);
		if (!file.isFile()) {
			throw new FileNotFoundException("File not found on server");
		}
		return file;
	}
}
