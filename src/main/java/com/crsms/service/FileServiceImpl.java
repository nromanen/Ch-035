package com.crsms.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.crsms.domain.Option;
import com.crsms.domain.Resource;
import com.crsms.domain.Resource.StorageType;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Service("fileService")
public class FileServiceImpl implements FileService {
	
	@Autowired
	private OptionService optionService;
	
	@Autowired
	private GoogleDriveService googleDriveService;
	
	private String generateUniqueFileName(String name) {
		return name.substring(0, name.lastIndexOf('.')) + "_" + new DateTime().toString(DateTimeFormat.forPattern("yyyy.MM.dd_kk.mm.ss.SSS")) 
				+ "_" + ThreadLocalRandom.current().nextInt(1000, 10000) + name.substring(name.lastIndexOf('.'), name.length());
	}
	
	private String getUniqueFileName(String name, File resourceDir) {
		File file = new File(resourceDir.getAbsoluteFile() + File.separator + name);
		if (file.isFile()) {
			name = generateUniqueFileName(name);
		}
		return name;
	}
	
	private String uploadToCatalinaHome(MultipartFile multipartFile)
			throws IOException {
        // Creating if not exist the directory to store file
        File resourceDir = new File(FileService.RESOURCE_PATH);
        if (!resourceDir.exists())
            resourceDir.mkdirs();
        String pathName = getUniqueFileName(multipartFile.getOriginalFilename(), resourceDir);
        String filePath = resourceDir.getAbsoluteFile() + File.separator + pathName;
        multipartFile.transferTo(new File(filePath));
        return pathName;
	}
	
	private com.google.api.services.drive.model.File uploadToGoogleDrive(MultipartFile multipartFile) 
			throws IOException {
		return googleDriveService.uploadToDrive(multipartFile);
	}

	private void prepareResponseHeaders(HttpServletResponse response,
			String mimeType, Integer contentLength, String fileName) {
		// set content attributes for the response
		response.setContentType(mimeType != null ? mimeType : MediaType.APPLICATION_OCTET_STREAM_VALUE);
		response.setContentLength(contentLength);
		// set other headers for the response
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
	}

	private void prepareResponseData(HttpServletResponse response,
			InputStream inputStream) throws IOException {
		// copy stream to response's OutputStream
		FileCopyUtils.copy(inputStream, response.getOutputStream());
		response.flushBuffer();
		response.getOutputStream().close();
		inputStream.close();
	}

	private void prepareCatalinaFileAttachmentResponse(String path, 
			HttpServletResponse response) throws IOException {
		File file = new File(FileService.RESOURCE_PATH + File.separator + path);
		if (!file.isFile()) {
			throw new FileNotFoundException("File not found on server");
		}
		prepareResponseHeaders(response, Files.probeContentType(file.toPath()), (int) file.length(), 
				file.getName());
		prepareResponseData(response, new FileInputStream(file));
	}
	
	private void prepareDriveFileAttachmentResponse(String path, 
			HttpServletResponse response) throws IOException {
		com.google.api.services.drive.model.File file = googleDriveService.getMediaFileFromDrive(path);
		prepareResponseHeaders(response, file.getMimeType(), file.getFileSize().intValue(), file.getTitle());
		prepareResponseData(response, googleDriveService.getMediaBytesFromDrive(path));
	}
	
	@Override
	public Resource.StorageType getResourceStorageTypeOption() {
		return Resource.StorageType.valueOf(optionService.getOptionsAsProperties().getProperty(Option.STORAGE_TYPE_OPTION_KEY));
	}

	@Override
	public String uploadFile(MultipartFile multipartFile, StorageType storageType) throws IOException {	
		switch(storageType) {
			case CATALINA:
				return uploadToCatalinaHome(multipartFile);
			case GOOGLE_DRIVE:
				return uploadToGoogleDrive(multipartFile).getId();
			default:
				// by conventions
				throw new FileSystemNotFoundException(
						"There is not suitable method for the " 
						+ storageType + " storage type");
		}
	}

	@Override
	public void prepareFileAttachmentResponse(String path, StorageType storageType, 
			HttpServletResponse response) throws IOException {
		switch(storageType) {
		case CATALINA:
			prepareCatalinaFileAttachmentResponse(path, response);
		case GOOGLE_DRIVE:
			prepareDriveFileAttachmentResponse(path, response);
		default:
			// by conventions
			throw new FileSystemNotFoundException(
					"There is not suitable method for the " 
					+ storageType + " storage type");
		}
	}
}
