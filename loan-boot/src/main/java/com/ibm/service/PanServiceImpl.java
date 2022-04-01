package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Customer;
import com.ibm.entity.Pan;
import com.ibm.exception.GlobalLoanException;
import com.ibm.repo.CustomerRepository;
import com.ibm.repo.PanRepository;


/**
 * Class {PanServiceImpl} is a service class extending {PanService}
 * for pan entity, which uses the methods from
 * pan repository.
 * 
 * @author Saswata Dutta
 */

@Service
public class PanServiceImpl implements PanService {
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private PanRepository panRepo;

	@Override
	public Pan createPan(Pan p) {
		return panRepo.save(p);
	}

	@Override
	public Pan updatePan(Pan p) throws GlobalLoanException{
		return panRepo.save(p);
	}

	@Override
	public Pan getPanByPanNo(String panNo) throws GlobalLoanException{
		return panRepo.findById(panNo)
				.orElseThrow(() -> new GlobalLoanException("404", "panNo not found"));
	}

	@Override
	public Pan getPanByCustomerId(int custId) throws GlobalLoanException{
		Customer cust = customerRepo.findById(custId).get();
		return panRepo.findByCustomer(cust);
	}
	
	// get cibil score of a customer
	@Override
	public int getCibilScore(int custId) throws GlobalLoanException{
		Customer cust = customerRepo.findById(custId).get();
		return panRepo.getCibilScore(cust);
	}

	@Override
	public List<Pan> getAllPans() {
		return panRepo.findAll();
	}

}
