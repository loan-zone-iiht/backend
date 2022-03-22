package com.ibm.service;

import com.ibm.entity.Manager;
import com.ibm.exception.GlobalLoanException;

public interface ManagerService {
	Manager createManager(Manager mgr) ;
	Manager loginManager(String email, String phone,String password) throws GlobalLoanException;
	Manager getRandomManager();
}
