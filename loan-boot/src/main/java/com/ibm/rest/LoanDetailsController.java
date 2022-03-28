package com.ibm.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Customer;
import com.ibm.entity.LoanDetails;
import com.ibm.entity.PaymentHistory;
import com.ibm.enums.StatusType;
import com.ibm.pojo.PaymentTransaction;
import com.ibm.pojo.UpdateLoanDetailsByStatus;
import com.ibm.service.LoanDetailsService;

@RestController
public class LoanDetailsController {
	@Autowired
	private LoanDetailsService loanDetailsService;

	@PostMapping(path = "/create-loandetail", consumes = "application/json")
	public LoanDetails createLoandetail(@RequestBody LoanDetails ld, @RequestParam int custId) {
		return loanDetailsService.createLoanDetails(ld, custId);
	}

	@GetMapping(path = "/get-loandetails", produces = "application/json")
	public List<LoanDetails> getAllLoanDetails() {
		return loanDetailsService.getAllLoanDetails();
	}

	@GetMapping(path = "/get-loandetails-by-status", produces = "application/json")
	public List<LoanDetails> getLoanDetailsByStatus(@RequestParam StatusType status) {
		System.out.println(status);
		return loanDetailsService.getLoandetailsByStatus(status);
	}

//	public List<LoanDetails> upadteLoanDetails(@RequestParam Map<String,String> reqParamMap) {
//	reqParamMap.entrySet();
	@PostMapping(path = "/update-loandetails-by-status", consumes = "application/json")
	public LoanDetails upadteLoanStatus(@RequestBody UpdateLoanDetailsByStatus reqPojo) {
//		System.err.println(status);
		// creating enums
//		StatusType sType = Enum.valueOf(StatusType.class, status);
//		System.err.println(sType);
		if (reqPojo.getLoanId() != 0) {
			return loanDetailsService.updateLoanStatusFromLoanId(reqPojo.getLoanId(), reqPojo.getStatus());
		} else
			return null;

	}

	@PostMapping(path = "/update-bank-to-cust-payout", consumes = "application/json")
	public LoanDetails updateBankToCustPayout(@RequestBody Map<String, String> reqBodyMap) {
		int loanId = Integer.parseInt(reqBodyMap.get("loanId"));
		boolean payout = Boolean.valueOf(reqBodyMap.get("payout"));
		LoanDetails ld = loanDetailsService.updateBankToCustPayout(loanId, payout);
		return ld;
	}
   
	@PostMapping(path = "/pay-back", consumes = "application/json")
	public PaymentHistory payBack(@RequestBody PaymentTransaction pt) {
		return loanDetailsService.payBack(pt);
	}
	@PostMapping(path = "/downpayment", consumes = "application/json")
	public PaymentHistory downpayment(@RequestBody PaymentTransaction pt) {
		return loanDetailsService.downpayment(pt);
	}
	@PostMapping(path = "/foreclosure-payment", consumes = "application/json")
	public PaymentHistory foreclosurePayment(@RequestBody PaymentTransaction pt) {
		return loanDetailsService.foreclosurePayment(pt);
	}
	
//	@GetMapping(path = "/get-outstanding-principal", produces = "application/json")
//	public double getOutstandingPrincipal(@RequestParam int loanId) {
//		return loanDetailsService.getOutstandingPrincipal(loanId);
//	}
	@GetMapping(path = "/get-payment-amount", produces = "application/json")
	public double getPaymentAmount(@RequestParam int loanId) {
		return loanDetailsService.getPaymentAmount(loanId);
	}

	// dev
	@PostMapping(path = "/update-loandetails", consumes = "application/json")
	public LoanDetails upadteLoanStatus(@RequestBody LoanDetails ld) {
		return loanDetailsService.updateLoanDetails(ld);
	}

	@GetMapping(path = "/get-loandetails/{loanId}", produces = "application/json")
	public LoanDetails getLoanDetailsByLoanId(@PathVariable int loanId) {
		return loanDetailsService.getLoanDetailsByLoanId(loanId);
	}

}
