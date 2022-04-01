package com.ibm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Customer;
import com.ibm.entity.LoanDetails;
import com.ibm.entity.PaymentHistory;

/**
 * Class {PaymentHistoryRepository} is a repository interface
 * for payment history entity.
 * 
 * @author Saswata Dutta
 */

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Integer>{
	List<PaymentHistory> findAllByCustomerId(int custId);
	List<PaymentHistory> findAllByLoanDetailsLoanId(int loanId);
}