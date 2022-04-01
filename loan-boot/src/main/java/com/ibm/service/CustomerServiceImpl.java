package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Customer;
import com.ibm.entity.Pan;
import com.ibm.enums.RoleOptions;
import com.ibm.exception.GlobalLoanException;
import com.ibm.repo.CustomerRepository;

/**
 * Class {CustomerServiceImpl} is a service class extending {CustomerService}
 * for customer entity, which uses the methods from
 * customer repository.
 * 
 * @author Saswata Dutta
 */

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private PanService panService;


	@Override
	public Customer createCustomer(Customer cust, String panNo) {
		Pan p = panService.getPanByPanNo(panNo); // fetching the pan via the panNo
		p.setCustomer(cust); // setting the customer(obj) for the pan
		cust.setPan(p); // setting the pan(obj) for the customer
		cust.setRole(RoleOptions.CUSTOMER);// setting the role
		return customerRepo.save(cust);
	}

	@Override
	public Customer updateCustomer(Customer cust){
		if(customerRepo.findById(cust.getId()).isPresent()) {
			return customerRepo.save(cust);			
		}else {
			throw new GlobalLoanException("404", "No customer exsists with this id");
		}
	}
	
	// To get a customer by pan no
	@Override
	public Customer getCustomerByPan(String panNo) throws GlobalLoanException {
		Pan p = panService.getPanByPanNo(panNo);
		return customerRepo.findByPan(p);
	}
	
	// To get a customer by their id
	@Override
	public Customer getCustomerById(int id) throws GlobalLoanException {
		return customerRepo.findById(id)
				.orElseThrow(() -> new GlobalLoanException("404","Customer id not found"));
	}
	
	
	// get all customers
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

}
