package com.ibm.service;

import java.util.List;

import com.ibm.entity.LoanDetails;
import com.ibm.entity.PaymentHistory;
import com.ibm.enums.StatusType;
import com.ibm.exception.GlobalLoanException;
import com.ibm.pojo.PaymentTransaction;

/**
 * Class {LoanDetailsService} is a service interface for loan details entity,
 * which uses the methods from loan details repository.
 * 
 * @author Saswata Dutta
 * @author Ashish Gupta
 */

public interface LoanDetailsService {
	LoanDetails createLoanDetails(LoanDetails ld, int custId);

	List<LoanDetails> getAllLoanDetails();

	List<LoanDetails> getLoandetailsByStatus(StatusType status);

	// middleware for admin needed
	LoanDetails updateLoanStatusFromLoanId(int loanId, StatusType status) throws GlobalLoanException;

//	// middleware for admin needed
//	LoanDetails updateLoanStatusFromCustId(int custId, StatusType status) throws GlobalLoanException; 
	LoanDetails updateBankToCustPayout(int loanId, boolean payout);

	PaymentHistory payBack(PaymentTransaction pt);

	PaymentHistory downpayment(PaymentTransaction pt);

	LoanDetails applyForForeclosure(int loanId);

	PaymentHistory foreclosurePayment(PaymentTransaction pt);

	LoanDetails getLoanDetailsByLoanId(int loanId) throws GlobalLoanException;

//	double getOutstandingPrincipal(int loanId);
//	void updateOutstandingPrincipal(int loanId, double op);
	double getPaymentAmount(int loanId);

	LoanDetails updateLoanDetails(LoanDetails ld) throws GlobalLoanException; // for dev only

	String getRejectionReason(int loanId); // Method to get the reason of rejection
}
