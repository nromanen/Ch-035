package com.crsms.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("multipartFileService")
public class MultipartFileServiceImpl implements MultipartFileService {
	
	private final String storagePath = "storage" + File.separator + "resources";
	
	@Override
	public void uploadFile(MultipartFile multipartFile) throws IOException {
		
		String originalName = multipartFile.getOriginalFilename();
		
		// TODO add this condition and some others to validator
		if (!multipartFile.isEmpty()) {
        	
        	try {
                // Creating if not exist the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + storagePath);
                if (!dir.exists())
                    dir.mkdirs();
                String filePath = dir.getAbsoluteFile() + File.separator + originalName;
                
                try {
                	multipartFile.transferTo(new File(filePath));
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    throw e;
                } catch (IOException e) {
                    e.printStackTrace();
                    throw e;
                }
                
                

            } catch (Exception e) {
            	throw new IOException(e);
            }
        } else {
            throw new IOException("File is empty");
        }
	}

	public String getStoragePath() {
		return storagePath;
	}
	
}
