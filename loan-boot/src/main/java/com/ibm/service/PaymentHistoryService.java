package com.ibm.service;

import java.util.List;

import com.ibm.entity.Customer;
import com.ibm.entity.LoanDetails;
import com.ibm.entity.PaymentHistory;

public interface PaymentHistoryService {
	PaymentHistory createPaymentHistory(PaymentHistory ph);

	PaymentHistory getPaymentHistoryById(int phId);

	List<PaymentHistory> findAllByCustomerId(int custId);

	List<PaymentHistory> findAllByLoanDetailsLoanId(int loanId);

}