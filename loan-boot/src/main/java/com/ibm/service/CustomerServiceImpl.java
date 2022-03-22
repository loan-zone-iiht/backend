package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Customer;
import com.ibm.entity.Pan;
import com.ibm.exception.GlobalLoanException;
import com.ibm.repo.CustomerRepository;

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
		return customerRepo.save(cust);
	}

	@Override
	public Customer updateCustomer(Customer cust) throws GlobalLoanException {
		return customerRepo.save(cust);
	}

	@Override
	public Customer getCustomerByPan(String panNo) throws GlobalLoanException {
		Pan p = panService.getPanByPanNo(panNo);
		return customerRepo.findByPan(p);
	}

	@Override
	public Customer getCustomerById(int id) throws GlobalLoanException {
		return customerRepo.findById(id)
				.orElseThrow(() -> new GlobalLoanException("404","Customer id not found"));
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

}
