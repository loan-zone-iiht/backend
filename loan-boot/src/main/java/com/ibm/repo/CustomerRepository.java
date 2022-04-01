package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Customer;
import com.ibm.entity.Pan;

/**
 * Class {CustomerRepository} is a repository interface
 * for customer entity.
 * 
 * @author Saswata Dutta
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findByPan(Pan p);

}
