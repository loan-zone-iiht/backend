package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ibm.entity.Customer;

import com.ibm.entity.Pan;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findByPan(Pan p);
	@Query("FROM Customer c Where c.email=?1 OR c.phone=?2")
	Customer loginCustomer(String email, String phone);
	Customer findByEmailOrPhone(String email, String phone);
}
