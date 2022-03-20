package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Customer;
import com.ibm.entity.Pan;
import com.ibm.service.CustomerService;
import com.ibm.service.PanService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;


	@PostMapping(path = "/create-customer", consumes = "application/json")
	public Customer createCustomer(@RequestBody Customer cust, @RequestParam String panNo) {
		return customerService.createCustomer(cust, panNo);

	}

	@PostMapping(path = "/update-customer", consumes = "application/json")
	public Customer updateCustomer(@RequestBody Customer cust) {
		return customerService.updateCustomer(cust);

	}

	@GetMapping(path = "/get-customers/{id}", produces = "application/json")
	public Customer getPanByPanNo(@PathVariable int id) {
		return customerService.getCustomerById(id);
	}
	@GetMapping(path = "/get-customers", produces = "application/json")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping(path = "/get-customer-by-pan", produces = "application/json")
	public Customer getPanByCustomer(@RequestParam String panNo) {
		return customerService.getCustomerByPan(panNo);
	}

}
