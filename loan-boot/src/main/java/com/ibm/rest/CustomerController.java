package com.ibm.rest;

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
import com.ibm.exception.GlobalLoanException;
import com.ibm.service.CustomerService;

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
	public Customer getCustomersById(@PathVariable int id) {
		try {
			return customerService.getCustomerById(id);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@GetMapping(path = "/get-customers", produces = "application/json")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping(path = "/get-customer-by-pan", produces = "application/json")
	public Customer getCustomersByPan(@RequestParam String panNo) {
		try {
			return customerService.getCustomerByPan(panNo);
		} catch (GlobalLoanException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

}
