package com.ibm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.entity.FileBlob;
import com.ibm.exception.GlobalLoanException;
import com.ibm.pojo.ResponseHeader;
import com.ibm.service.CustomerService;
import com.ibm.service.FileBlobService;

/**
 * Class {FileBlobController} is the controller class. Mainly having the routes
 * related to fileblob entity. Mainly uses FileBlobService methods.
 * 
 * 
 * 
 * 
 * @author Saswata Dutta
 */

@CrossOrigin
@RestController
public class FileBlobController {
	@Autowired
	private FileBlobService fileBlobService;
	private ResponseHeader rh;

	@PostMapping(path = "/upload-profile-pic")
	public ResponseEntity<FileBlob> uploadProfilePic(@RequestBody MultipartFile file, @RequestParam int custId) {
		try {
//			return fileBlobService.saveProfilePic(custId, file);
			rh = new ResponseHeader();
			rh.putOnMap("success", "true");
			ResponseEntity<FileBlob> res = new ResponseEntity<FileBlob>(fileBlobService.saveProfilePic(custId, file),
					rh.getHeaders(), HttpStatus.OK);
			return res;
		} catch (IllegalArgumentException e) {
			throw new GlobalLoanException("400", "Error uploading file/ try reselecting the file" + e.getMessage());
		}
	}

	@GetMapping(path = "/get-profile-pic")
	public ResponseEntity<byte[]> getProfilePic(@RequestParam int custId) {
//			return fileBlobService.getProfilePic(custId)
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<byte[]> res = new ResponseEntity<byte[]>(fileBlobService.getProfilePic(custId), rh.getHeaders(),
				HttpStatus.OK);
		return res;

	}
	
	@PostMapping(path = "/upload-suporting-doc")
	public ResponseEntity<FileBlob> uploadSupportingDoc(@RequestBody MultipartFile file, @RequestParam int custId) {
		try {
//			return fileBlobService.saveProfilePic(custId, file);
			rh = new ResponseHeader();
			rh.putOnMap("success", "true");
			ResponseEntity<FileBlob> res = new ResponseEntity<FileBlob>(fileBlobService.saveSupportingDoc(custId, file),
					rh.getHeaders(), HttpStatus.OK);
			return res;
		} catch (IllegalArgumentException e) {
			throw new GlobalLoanException("400", "Error uploading file/ try reselecting the file" + e.getMessage());
		}
	}
}
