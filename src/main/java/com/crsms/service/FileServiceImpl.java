package com.crsms.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("fileService")
public class FileServiceImpl implements FileService {
	
	private final String storagePath = "storage" + File.separator + "resources";
	
	@Override
	public void uploadFile(MultipartFile multipartFile) throws IOException {	
		String originalName = multipartFile.getOriginalFilename();
        // Creating if not exist the directory to store file
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + storagePath);
        if (!dir.exists())
            dir.mkdirs();
        String filePath = dir.getAbsoluteFile() + File.separator + originalName;
        multipartFile.transferTo(new File(filePath));
	}

	@Override
	public File getFileForDownload(String fileName) throws IOException {
		String rootPath = System.getProperty("catalina.home");
		File file = new File(rootPath + File.separator + storagePath + File.separator + fileName);
		if (!file.isFile()) {
			throw new FileNotFoundException();
		}
		return file;
	}

	public String getStoragePath() {
		return storagePath;
	}
	
}
