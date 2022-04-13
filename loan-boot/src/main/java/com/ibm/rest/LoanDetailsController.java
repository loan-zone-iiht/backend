package com.ibm.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.LoanDetails;
import com.ibm.entity.PaymentHistory;
import com.ibm.enums.StatusType;
import com.ibm.pojo.PaymentTransaction;
import com.ibm.pojo.ResponseHeader;
import com.ibm.pojo.UpdateLoanDetailsByStatus;
import com.ibm.service.LoanDetailsService;

/**
 * Class {LoanDetailsController} is the controller class. Mainly having the
 * routes related to loan details entity. Mainly uses LoanDetailsService
 * methods.
 * 
 * Controller paths starting with /manager/ or /manager- needs a header role and
 * it should be MANAGER which is a enum of type RoleOptions.
 * 
 * @author Saswata Dutta
 * @author Ashish Gupta
 */

@CrossOrigin
@RestController
public class LoanDetailsController {
	@Autowired
	private LoanDetailsService loanDetailsService;
	private ResponseHeader rh;

	@PostMapping(path = "/create-loandetail", consumes = "application/json")
	public ResponseEntity<LoanDetails> createLoandetail(@RequestBody LoanDetails ld, @RequestParam int custId) {
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<LoanDetails> res = new ResponseEntity<LoanDetails>(
				loanDetailsService.createLoanDetails(ld, custId), rh.getHeaders(), HttpStatus.OK);
		return res;
	}

	@GetMapping(path = "/manager/get-loandetails", produces = "application/json")
	public ResponseEntity<List<LoanDetails>> getAllLoanDetails() {

//		return loanDetailsService.getAllLoanDetails();
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<List<LoanDetails>> res = new ResponseEntity<List<LoanDetails>>(
				loanDetailsService.getAllLoanDetails(), rh.getHeaders(), HttpStatus.OK);
		return res;
	}

	@GetMapping(path = "/manager/get-loandetails-by-status", produces = "application/json")
	public ResponseEntity<List<LoanDetails>> getLoanDetailsByStatus(@RequestParam StatusType status) {
//		System.out.println(status);
//		return loanDetailsService.getLoandetailsByStatus(status);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<List<LoanDetails>> res = new ResponseEntity<List<LoanDetails>>(
				loanDetailsService.getLoandetailsByStatus(status), rh.getHeaders(), HttpStatus.OK);
		return res;
	}

//	public List<LoanDetails> upadteLoanDetails(@RequestParam Map<String,String> reqParamMap) {
//	reqParamMap.entrySet();
	@PostMapping(path = "/manager/update-loandetails-by-status", consumes = "application/json")
	public ResponseEntity<LoanDetails> upadteLoanStatus(@RequestBody UpdateLoanDetailsByStatus reqPojo) {
		// creating enums
//		StatusType sType = Enum.valueOf(StatusType.class, status);
//		System.err.println(sType);
		if (reqPojo.getLoanId() != 0) {

//			return loanDetailsService.updateLoanStatusFromLoanId(reqPojo.getLoanId(), reqPojo.getStatus());
			rh = new ResponseHeader();
			rh.putOnMap("success", "true");
			ResponseEntity<LoanDetails> res = new ResponseEntity<LoanDetails>(
					loanDetailsService.updateLoanStatusFromLoanId(reqPojo.getLoanId(), reqPojo.getStatus()),
					rh.getHeaders(), HttpStatus.OK);
			return res;
		} else
			return null;

	}

	@PostMapping(path = "/apply-for-foreclosure", consumes = "application/json")
	public ResponseEntity<LoanDetails> applyForForeclosure(@RequestBody UpdateLoanDetailsByStatus reqPojo) {
		if (reqPojo.getLoanId() != 0) {
//			return loanDetailsService.updateLoanStatusFromLoanId(reqPojo.getLoanId(), reqPojo.getStatus());
			rh = new ResponseHeader();
			rh.putOnMap("success", "true");
			ResponseEntity<LoanDetails> res = new ResponseEntity<LoanDetails>(
					loanDetailsService.applyForForeclosure(reqPojo.getLoanId()), rh.getHeaders(), HttpStatus.OK);
			return res;
		} else
			return null;

	}

	@PostMapping(path = "/manager/update-bank-to-cust-payout", consumes = "application/json")
	public ResponseEntity<LoanDetails> updateBankToCustPayout(@RequestBody Map<String, String> reqBodyMap) {
		int loanId = Integer.parseInt(reqBodyMap.get("loanId"));
		boolean payout = Boolean.valueOf(reqBodyMap.get("payout"));
		LoanDetails ld = loanDetailsService.updateBankToCustPayout(loanId, payout);
//		return ld;
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<LoanDetails> res = new ResponseEntity<LoanDetails>(ld, rh.getHeaders(), HttpStatus.OK);
		return res;
	}

//	@GetMapping(path = "/get-outstanding-principal", produces = "application/json")
//	public double getOutstandingPrincipal(@RequestParam int loanId) {
//		return loanDetailsService.getOutstandingPrincipal(loanId);
//	}

	@PostMapping(path = "/pay-back", consumes = "application/json")
	public ResponseEntity<PaymentHistory> payBack(@RequestBody PaymentTransaction pt) {
//		return loanDetailsService.payBack(pt);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<PaymentHistory> res = new ResponseEntity<PaymentHistory>(loanDetailsService.payBack(pt),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}

	@PostMapping(path = "/downpayment", consumes = "application/json")
	public ResponseEntity<PaymentHistory> downpayment(@RequestBody PaymentTransaction pt) {
//		return loanDetailsService.downpayment(pt);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<PaymentHistory> res = new ResponseEntity<PaymentHistory>(loanDetailsService.downpayment(pt),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}

	@PostMapping(path = "/foreclosure-payment", consumes = "application/json")
	public ResponseEntity<PaymentHistory> foreclosurePayment(@RequestBody PaymentTransaction pt) {
//		return loanDetailsService.foreclosurePayment(pt);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<PaymentHistory> res = new ResponseEntity<PaymentHistory>(
				loanDetailsService.foreclosurePayment(pt), rh.getHeaders(), HttpStatus.OK);
		return res;
	}

	@GetMapping(path = "/get-outstanding-principal", produces = "application/json")
	public double getOutstandingPrincipal(@RequestParam int loanId) {
		return loanDetailsService.getOutstandingPrincipal(loanId);
	}

	// to know the next payment amount
	@GetMapping(path = "/get-payment-amount", produces = "application/json")
	public ResponseEntity<Double> getPaymentAmount(@RequestParam int loanId) {
//		return loanDetailsService.getPaymentAmount(loanId);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<Double> res = new ResponseEntity<Double>(loanDetailsService.getPaymentAmount(loanId),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}

	@GetMapping(path = "/get-loandetails/{loanId}", produces = "application/json")
	public ResponseEntity<LoanDetails> getLoanDetailsByLoanId(@PathVariable int loanId) {
//		return loanDetailsService.getLoanDetailsByLoanId(loanId);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<LoanDetails> res = new ResponseEntity<LoanDetails>(
				loanDetailsService.getLoanDetailsByLoanId(loanId), rh.getHeaders(), HttpStatus.OK);
		return res;
	}

	// dev
	@PostMapping(path = "/update-loandetails", consumes = "application/json")
	public ResponseEntity<LoanDetails> upadteLoanStatus(@RequestBody LoanDetails ld) {
//		return loanDetailsService.updateLoanDetails(ld);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<LoanDetails> res = new ResponseEntity<LoanDetails>(loanDetailsService.updateLoanDetails(ld),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}

	@PostMapping(path = "/rejection-reason", produces = "application/json")
	public ResponseEntity<String> rejectionReason(@RequestBody Map<Integer, String> reqBodyMap) {
		loanDetailsService.getRejectionReason(Integer.parseInt(reqBodyMap.get("loanId")),
				reqBodyMap.get("rect_reason"));

		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<String> res = new ResponseEntity<String>("Rejection reason updated.", rh.getHeaders(),
				HttpStatus.OK);
		return res;
	}
}
