package com.ibm.service;

import java.util.List;

import com.ibm.entity.LoanDetails;
import com.ibm.enums.StatusType;

public interface LoanDetailsService {
	LoanDetails createLoanDetails(LoanDetails ld, int custId);
	List<LoanDetails> getAllLoanDetails();
	List<LoanDetails> getLoandetailsByStatus(StatusType status);
	LoanDetails updateLoanStatusFromLoanId(int loanId, StatusType status); // middleware for admin needed
	LoanDetails updateLoanStatusFromCustId(int custId, StatusType status); // middleware for admin needed
	LoanDetails updateBankToCustPayout(int loanId, boolean payout);
	double getOutstandingPrincipal(int loanId);
	void updateOutstandingPrincipal(int loanId, double op);
	LoanDetails updaLoanDetails(LoanDetails ld); // for dev only
}
