package com.ibm.rest;

import java.util.List;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.ibm.pojo.ResponseHeader;
import com.ibm.service.CustomerService;

/**
 * Class {CustomerController} is the controller class. Mainly having the routes
 * related to customer entity. Mainly uses CustomerService methods.
 * 
 * Controller paths starting with /manager/ or /manager- needs a header role and
 * it should be MANAGER which is a enum of type RoleOptions.
 * 
 * @author Saswata Dutta
 */

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	private ResponseHeader rh;

	@PostMapping(path = "/create-customer", consumes = "application/json")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer cust, @RequestParam String panNo) {
//		return customerService.createCustomer(cust, panNo);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<Customer> res = new ResponseEntity<Customer>(customerService.createCustomer(cust, panNo),
				rh.getHeaders(), HttpStatus.OK);
		return res;

	}

	@PostMapping(path = "/update-customer", consumes = "application/json")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer cust) {
//		return customerService.updateCustomer(cust);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<Customer> res = new ResponseEntity<Customer>(customerService.updateCustomer(cust),
				rh.getHeaders(), HttpStatus.OK);
		return res;

	}

	@GetMapping(path = "/get-customers/{id}", produces = "application/json")
	public ResponseEntity<Customer> getCustomersById(@PathVariable int id) {
		try {
//			return customerService.getCustomerById(id);
			rh = new ResponseHeader();
			rh.putOnMap("success", "true");
			ResponseEntity<Customer> res = new ResponseEntity<Customer>(customerService.getCustomerById(id),
					rh.getHeaders(), HttpStatus.OK);
			return res;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@GetMapping(path = "/manager/get-customers", produces = "application/json")
	public ResponseEntity<List<Customer>> getAllCustomers() {
//		return customerService.getAllCustomers();
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<List<Customer>> res = new ResponseEntity<List<Customer>>(customerService.getAllCustomers(),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}

	@GetMapping(path = "/manager/get-customer-by-pan", produces = "application/json")
	public ResponseEntity<Customer> getCustomersByPan(@RequestParam String panNo) {
		try {
//			return customerService.getCustomerByPan(panNo);
			rh = new ResponseHeader();
			rh.putOnMap("success", "true");
			ResponseEntity<Customer> res = new ResponseEntity<Customer>(customerService.getCustomerByPan(panNo),
					rh.getHeaders(), HttpStatus.OK);
			return res;

		} catch (GlobalLoanException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

}
