package com.ibm.service;

import java.util.List;

import com.ibm.entity.LoanDetails;
import com.ibm.enums.StatusType;
import com.ibm.exception.GlobalLoanException;

public interface LoanDetailsService {
	LoanDetails createLoanDetails(LoanDetails ld, int custId);
	List<LoanDetails> getAllLoanDetails();
	List<LoanDetails> getLoandetailsByStatus(StatusType status);
	// middleware for admin needed
	LoanDetails updateLoanStatusFromLoanId(int loanId, StatusType status) throws GlobalLoanException; 
	// middleware for admin needed
	LoanDetails updateLoanStatusFromCustId(int custId, StatusType status) throws GlobalLoanException; 
	LoanDetails updateBankToCustPayout(int loanId, boolean payout);
	double getOutstandingPrincipal(int loanId);
	void updateOutstandingPrincipal(int loanId, double op);
	double paymentAmount(int loanId);
	LoanDetails updateLoanDetails(LoanDetails ld)throws GlobalLoanException;; // for dev only
	LoanDetails getLoanDetailsByLoanId(int loanId)throws GlobalLoanException;;// for dev only
}
