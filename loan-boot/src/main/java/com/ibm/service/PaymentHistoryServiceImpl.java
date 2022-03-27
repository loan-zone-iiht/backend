package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.PaymentHistory;
import com.ibm.exception.GlobalLoanException;
import com.ibm.repo.PaymentHistoryRepository;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

	@Autowired
	private PaymentHistoryRepository paymentHistoryRepo;

	@Override
	public PaymentHistory createPaymentHistory(PaymentHistory ph) {
		return paymentHistoryRepo.save(ph);
	}

	@Override
	public PaymentHistory getPaymentHistoryById(int phId) {
		return paymentHistoryRepo.findById(phId)
				.orElseThrow(() -> (new GlobalLoanException("404", "No paymentHistory with this id")));
	}
	
	@Override
	public List<PaymentHistory> findAllByCustomerId(int custId) {
		return paymentHistoryRepo.findAllByCustomerId(custId);
	}
	
	// get all the payment history of all customer
	@Override
	public List<PaymentHistory> findAllByLoanDetailsLoanId(int loanId) {
		return paymentHistoryRepo.findAllByLoanDetailsLoanId(loanId);
	}

}
