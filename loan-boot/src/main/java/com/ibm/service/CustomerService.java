package com.ibm.service;

import java.util.List;

import com.ibm.entity.Customer;
import com.ibm.entity.Manager;
import com.ibm.exception.GlobalLoanException;

/**
 * Class {CustomerService} is a service interface
 * for customer entity, which uses the methods from
 * customer repository.
 * 
 * @author Subhajit Sanyal
 * @author Saswata Dutta
 */


public interface CustomerService {
	Customer createCustomer(Customer cust, String panNo);
	Customer updateCustomer(Customer cust) throws GlobalLoanException;
	Customer getCustomerByPan(String panNo) throws GlobalLoanException;
	Customer getCustomerById(int id) throws GlobalLoanException;
	List<Customer> getAllCustomers();
	Customer loginCustomer(String email, String phone, String password,int otp) throws GlobalLoanException;
	Customer sendOtp(String email, String phone);
	boolean verifyOtp(Customer cus, int otp);
	
}
