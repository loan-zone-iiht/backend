package com.ibm.service;

import com.ibm.entity.Customer;
import com.ibm.entity.Pan;

public interface CustomerService {
	Customer createCustomer(Customer cust, String panNo);
	Customer updateCustomer(Customer cust);
	Customer getCustomerByPan(String panNo);
	Customer getCustomerById(int id);
}
