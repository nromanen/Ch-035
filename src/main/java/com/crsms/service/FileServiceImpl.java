package com.crsms.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("fileService")
public class FileServiceImpl implements FileService {
	
	public static final String ROOT_PATH = System.getProperty("catalina.home");
	public static final String STORAGE_PATH =  ROOT_PATH + File.separator + "storage";
	public static final String RESOURCE_PATH = STORAGE_PATH + File.separator + "resources";
	
	@Override
	public void uploadFile(MultipartFile multipartFile) throws IOException {	
		String originalName = multipartFile.getOriginalFilename();
        // Creating if not exist the directory to store file
        File dir = new File(RESOURCE_PATH);
        if (!dir.exists())
            dir.mkdirs();
        String filePath = dir.getAbsoluteFile() + File.separator + originalName;
        multipartFile.transferTo(new File(filePath));
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
