package com.ibm.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.entity.Customer;
import com.ibm.entity.FileBlob;
import com.ibm.exception.GlobalLoanException;
import com.ibm.repo.CustomerRepository;
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
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public FileBlob saveProfilePic(int custId, MultipartFile file) {
		try {
			Customer cust = custService.getCustomerById(custId);
			String fileName = StringUtils.cleanPath("custId-" + custId + "-" + LocalDate.now() + "-" + LocalTime.now()
					+ "-" + file.getOriginalFilename());
			FileBlob profileBlob = new FileBlob(cust, fileName, file.getContentType(), true, file.getBytes());
			profileBlob = fileBlobRepo.save(profileBlob);
			// setting profile pic in customer entity
			cust.setProfilePic(profileBlob);
			customerRepo.save(cust);
			return profileBlob;

			/**
			 * Don't delete. File download uri logic. Will need later.
			 */
			// file download uri name & extension
//			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
//			String fileNameDownload = StringUtils.cleanPath(custId + "- profile");
			// file download uri creation
//			String fileDownloadUri = ServletUriComponentsBuilder
//			          .fromCurrentContextPath()
//			          .path("/files/")
//			          .path(Integer.toString(profileBlob.getId()))
//			          .toUriString();
//			// file download uri set
//			profileBlob.setFileUrl(fileDownloadUri);

			// returning the final file response
//			return fileBlobRepo.save(profileBlob);
		} catch (Exception e) {
			throw new GlobalLoanException("400", "Error getting file data" + e.getLocalizedMessage());
		}
	}

	@Override
	public byte[] getProfilePic(int custId) {
		try {

			Customer cust = custService.getCustomerById(custId);
			return cust.getProfilePic().getDataBlob();
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			throw new GlobalLoanException("400", "No profile picture for user");
		}
	}

	@Override
	public FileBlob saveSupportingDoc(int custId, MultipartFile file) {
		try {
			Customer cust = custService.getCustomerById(custId);
			String fileName = StringUtils.cleanPath("custId-" + custId + "-" + LocalDate.now() + "-" + LocalTime.now()
					+ "-" + file.getOriginalFilename());
			FileBlob docBlob = new FileBlob(cust, fileName, file.getContentType(), false, file.getBytes());
			docBlob = fileBlobRepo.save(docBlob);
			// setting profile pic in customer entity
			cust.setSupportingDoc(docBlob);
			customerRepo.save(cust);
			return docBlob;

		} catch (Exception e) {
			throw new GlobalLoanException("400", "Error getting file data" + e.getLocalizedMessage());
		}
	}

	@Override
	public byte[] getSupportingDoc(int custId) {
		try {
			Customer cust = custService.getCustomerById(custId);
			return cust.getSupportingDoc().getDataBlob();
		} catch (Exception e) {
			throw new GlobalLoanException("400", "No supporting document for user");
		}
	}

}
