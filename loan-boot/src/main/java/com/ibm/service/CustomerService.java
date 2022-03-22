package com.ibm.service;

import java.util.List;

import com.ibm.entity.Customer;
import com.ibm.exception.GlobalLoanException;

public interface CustomerService {
	Customer createCustomer(Customer cust, String panNo);
	Customer updateCustomer(Customer cust) throws GlobalLoanException;
	Customer getCustomerByPan(String panNo) throws GlobalLoanException;
	Customer getCustomerById(int id) throws GlobalLoanException;
	List<Customer> getAllCustomers();
}
