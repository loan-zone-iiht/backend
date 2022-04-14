package com.ibm.service;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.entity.Customer;
import com.ibm.entity.FileBlob;
import com.ibm.exception.GlobalLoanException;
import com.ibm.repo.FileBlobRepository;

/**
 * Class {FileBlobServiceImpl} is a service class extending {FileBlobService}
 * for file blob entity, which uses the methods from customer repository.
 * 
 * @author Saswata Dutta
 */

@Service
public class FileBlobServiceImpl implements FileBlobService {
	@Autowired
	private FileBlobRepository fileBlobRepo;
	@Autowired
	private CustomerService custService;

	@Override
	public FileBlob saveProfilePic(int custId, MultipartFile file) {
		try {
			Customer cust = custService.getCustomerById(custId);
			String fileName = StringUtils.cleanPath(custId + "-" + file.getOriginalFilename() + "-" + LocalDate.now());
			FileBlob profileBlob = new FileBlob(cust, fileName, file.getContentType(), true, file.getBytes());
			return fileBlobRepo.save(profileBlob);
		} catch (Exception e) {
			throw new GlobalLoanException("400", "Error getting file data");
		}
	}

}
