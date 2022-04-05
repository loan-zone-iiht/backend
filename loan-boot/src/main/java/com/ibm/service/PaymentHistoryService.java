package com.ibm.service;

import java.util.List;

import com.ibm.entity.Customer;
import com.ibm.entity.LoanDetails;
import com.ibm.entity.PaymentHistory;
/**
 * Class {PaymentHistoryService} is a service interface
 * for payment history entity, which uses the methods from
 * payment history repository.
 * 
 * @author Saswata Dutta
 */
public interface PaymentHistoryService {
	PaymentHistory createPaymentHistory(PaymentHistory ph);

	PaymentHistory getPaymentHistoryById(int phId);

	List<PaymentHistory> findAllByCustomerId(int custId);

	List<PaymentHistory> findAllByLoanDetailsLoanId(int loanId);

}
