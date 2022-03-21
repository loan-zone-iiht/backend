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
import com.ibm.enums.StatusType;
import com.ibm.service.LoanDetailsService;

@RestController
public class LoanDetailsClass {
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
	public LoanDetails upadteLoanDetails(@RequestParam(name = "status") StatusType status,
			@RequestParam(required = false, name = "loanId") int loanId, 
			@RequestParam(required = false, name = "custId") int custId) {
//		System.err.println(status);
//		StatusType sType = Enum.valueOf(StatusType.class, status);
//		System.err.println(sType);
		if(loanId != 0) {
			return loanDetailsService.updateLoanStatusFromLoanId(loanId, status);
		}else if(custId != 0) {			
			return loanDetailsService.updateLoanStatusFromCustId(custId, status);
		}else {
			return null;
		}
		
	}

}
