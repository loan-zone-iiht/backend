package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.LoanDetails;
import com.ibm.entity.Manager;
import com.ibm.entity.PaymentHistory;
import com.ibm.service.PaymentHistoryService;

/**
 * Class {PaymentHistoryController} is the controller class.
 * Mainly having the routes related to payment history entity.
 * Mainly uses PaymentHistoryService methods.
 * 
 * @author Saswata Dutta
 */

@RestController
public class PaymentHistoryController {
	@Autowired
	private PaymentHistoryService paymneHistoryService;

	@GetMapping(path = "/get-payment-history/{phId}", produces = "application/json")
	public PaymentHistory getPaymentHistoryById(@PathVariable int phId) {
		return paymneHistoryService.getPaymentHistoryById(phId);
	}

	@GetMapping(path = "/list-payment-history-by-cust-id", produces = "application/json")
	public List<PaymentHistory> listPaymentHistoryByCustId(@RequestParam int custId) {
		return paymneHistoryService.findAllByCustomerId(custId);
	}
	
	@GetMapping(path = "/list-payment-history-by-loan-id", produces = "application/json")
	public List<PaymentHistory> listPaymentHistoryByLoanId(@RequestParam int loanId) {
		return paymneHistoryService.findAllByLoanDetailsLoanId(loanId);
	}

}
