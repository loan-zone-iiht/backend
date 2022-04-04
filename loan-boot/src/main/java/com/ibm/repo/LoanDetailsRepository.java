package com.ibm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Customer;
import com.ibm.entity.LoanDetails;
import com.ibm.enums.StatusType;

/**
 * Class {LoanDetailsRepository} is a repository interface
 * for Loan details entity.
 * 
 * @author Saswata Dutta
 * @author Subhajir Sanyal
 */

public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Integer> {
	List<LoanDetails> findAllByLoanStatus(StatusType status);

	LoanDetails findByCustomer(Customer cust);
	LoanDetails findByloanId(int id);
}
