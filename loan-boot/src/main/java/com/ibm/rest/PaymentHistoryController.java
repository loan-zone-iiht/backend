package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.PaymentHistory;
import com.ibm.pojo.ResponseHeader;
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
	private PaymentHistoryService paymentHistoryService;
	private ResponseHeader rh;

	@GetMapping(path = "/get-payment-history/{phId}", produces = "application/json")
	public ResponseEntity<PaymentHistory> getPaymentHistoryById(@PathVariable int phId) {
//		return paymentHistoryService.getPaymentHistoryById(phId);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<PaymentHistory> res = new ResponseEntity<PaymentHistory>(
				paymentHistoryService.getPaymentHistoryById(phId),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}

	@GetMapping(path = "/list-payment-history-by-cust-id", produces = "application/json")
	public ResponseEntity<List<PaymentHistory>> listPaymentHistoryByCustId(@RequestParam int custId) {
//		return paymentHistoryService.findAllByCustomerId(custId);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<List<PaymentHistory>> res = new ResponseEntity<List<PaymentHistory>>(
				paymentHistoryService.findAllByCustomerId(custId),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}
	
	@GetMapping(path = "/list-payment-history-by-loan-id", produces = "application/json")
	public ResponseEntity<List<PaymentHistory>> listPaymentHistoryByLoanId(@RequestParam int loanId) {
//		return paymentHistoryService.findAllByLoanDetailsLoanId(loanId);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<List<PaymentHistory>> res = new ResponseEntity<List<PaymentHistory>>(
				paymentHistoryService.findAllByLoanDetailsLoanId(loanId),
				rh.getHeaders(), HttpStatus.OK);
		return res;
		
	}

}
