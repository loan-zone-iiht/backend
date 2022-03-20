package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Customer;
import com.ibm.entity.Pan;
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
	public Customer updateCustomer(Customer cust) {
		return customerRepo.save(cust);
	}

	@Override
	public Customer getCustomerByPan(String panNo) {
		Pan p = panService.getPanByPanNo(panNo);
		return customerRepo.findByPan(p);
	}

	@Override
	public Customer getCustomerById(int id) {
		return customerRepo.findById(id).get();
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

}
