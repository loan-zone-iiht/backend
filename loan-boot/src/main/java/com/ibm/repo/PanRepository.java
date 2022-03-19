package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ibm.entity.Customer;
import com.ibm.entity.Pan;

public interface PanRepository extends JpaRepository<Pan, String>{
	Pan findByCustomer(Customer cust);
	@Query("SELECT p.cibilScore FROM Pan p WHERE customer=?1")
	int getCibilScore(Customer cust);
}
