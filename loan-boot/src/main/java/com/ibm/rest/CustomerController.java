package com.ibm.rest;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ibm.entity.Customer;
import com.ibm.entity.Manager;
import com.ibm.exception.GlobalLoanException;
import com.ibm.pojo.LoginPOJO;
import com.ibm.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping(path = "/customer-signup", consumes = "application/json")
	public Customer createCustomer(@RequestBody Customer cust, @RequestParam String panNo) {
		return customerService.createCustomer(cust, panNo);

	}

	@PostMapping(path = "/customer-login", consumes = "application/json")
	public Customer customerLogin(@RequestBody LoginPOJO login) {

		return customerService.loginCustomer(login.getEmail(), login.getPhone(), login.getPassword(),login.getOtp());
	}
	@PostMapping(path = "/customer-send-otp", consumes = "application/json")
	public Customer customerCheckOtp(@RequestBody LoginPOJO login) {

		return customerService.sendOtp(login.getEmail(), login.getPhone());
	}
	

	@PostMapping(path = "/update-customer", consumes = "application/json")
	public Customer updateCustomer(@RequestBody Customer cust) {
		return customerService.updateCustomer(cust);

	}
	@PostMapping(path = "/get-customer-limit", consumes = "application/json")
	//public List<Integer> get_cus_limit(@RequestParam int cusID) {
	public ResponseEntity<List<Integer>> get_cus_limit(@RequestParam int cusID) {
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		Customer cust=customerService.getCustomerById(cusID);
		int civ=cust.getPan().getCibilScore();
		int roi;
		int principal;
		List<Integer> list = new ArrayList<Integer>();
		if (civ<500) {
			roi=100;
			principal=0;
		}
		else if (civ>=500 && civ<600) {
			roi=15;
			principal=(int) fetch_principal(cust,roi);

		}
		else {
			roi=12;
			principal=(int) fetch_principal(cust,roi);
		}
		list.add(roi);
		list.add(principal);	
		ResponseEntity<List<Integer>> res = new ResponseEntity<List<Integer>>(list,rh.getHeaders(), HttpStatus.OK);
		return res;
		//return list;

	}

	private double fetch_principal(Customer cust,int roi) {
		double e=(cust.getSalary()/100)*20;
		double principal=(e*20*roi)/(((roi*20)/100)+1);
		return principal;
	}

	@GetMapping(path = "/get-customers/{id}", produces = "application/json")
	public Customer getCustomersById(@PathVariable int id) {
		try {
			return customerService.getCustomerById(id);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@GetMapping(path = "/manager/get-customers", produces = "application/json")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping(path = "/manager/get-customer-by-pan", produces = "application/json")
	public Customer getCustomersByPan(@RequestParam String panNo) {
		try {
			return customerService.getCustomerByPan(panNo);
		} catch (GlobalLoanException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

}
