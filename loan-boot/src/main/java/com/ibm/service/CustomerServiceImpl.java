package com.ibm.service;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.email.MailSender;
import com.ibm.entity.Customer;
import com.ibm.entity.Manager;
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
 * @author Subhajit Sanyal
 */

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private PanService panService;
	@Autowired
	private MailSender mailSender;

	Supplier<Integer> generateOtp = () -> {
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);

		return n;
	};

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
	@Override
	public Customer loginCustomer(String email, String phone, String password,int otp) throws GlobalLoanException {
		Customer cus = customerRepo.loginCustomer(email, phone);
		System.err.println(cus);
		if(cus == null) {
			throw new GlobalLoanException("404", "No customer with this credential");
		}else if (cus.getPassword().equals(password)) {
			return cus;
		} 		
		else if(this.verifyOtp(cus, otp)){
			// verify otp
			return cus;
		}
		else {
			throw new GlobalLoanException("403", "Wrong password");
		}
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
	@Override
	public boolean verifyOtp(Customer cus, int otp) {
		if (cus.getOtp() == otp) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Customer sendOtp(String email, String phone) {

		Customer cus = customerRepo.findByEmailOrPhone(email, phone);
		// generating otp
		int generatedOtp = generateOtp.get();
		cus.setOtp(generatedOtp);
		customerRepo.save(cus);

		// sending mail
		mailSender.setEmailSubject("OTP from LOAN ZONE");
		mailSender.setReceiverEmail(cus.getEmail());
		mailSender.setEmailContent("Customer OTP: " + generatedOtp);
		mailSender.sendEmail();

		return cus;
	}
}
