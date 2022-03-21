package com.ibm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Customer;
import com.ibm.entity.LoanDetails;
import com.ibm.enums.StatusType;

public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Integer> {
	List<LoanDetails> findAllByLoanStatus(StatusType status);

	LoanDetails findAllByCustomer(Customer cust);
}
