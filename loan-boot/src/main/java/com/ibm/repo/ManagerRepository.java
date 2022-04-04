package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ibm.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
	@Query(value = "SELECT * FROM loan_managers_boot ORDER BY RAND() LIMIT 1", nativeQuery = true)
	Manager getRandomManager();
	@Query("FROM Manager m Where m.email=?1 OR m.phone=?2")
	Manager loginManager(String email, String phone);
	Manager findByEmailOrPhone(String email, String phone);
}
