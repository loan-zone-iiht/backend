package com.ibm.service;

import java.util.List;

import com.ibm.entity.Customer;

public interface CustomerService {
	Customer createCustomer(Customer cust, String panNo);
	Customer updateCustomer(Customer cust);
	Customer getCustomerByPan(String panNo);
	Customer getCustomerById(int id);
	List<Customer> getAllCustomers();
}
