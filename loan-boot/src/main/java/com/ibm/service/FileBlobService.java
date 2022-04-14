package com.ibm.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.ibm.entity.FileBlob;

/**
 * Class {FileBlobService} is a service interface for FileBlob entity, which
 * uses the methods from file blob repository.
 * 
 * 
 * @author Saswata Dutta
 */

public interface FileBlobService {
	FileBlob saveProfilePic(int custId, MultipartFile file);
	
}
