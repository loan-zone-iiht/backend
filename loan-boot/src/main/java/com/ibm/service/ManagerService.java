package com.ibm.service;

import com.ibm.entity.Manager;
import com.ibm.exception.GlobalLoanException;

public interface ManagerService {
	Manager createManager(Manager mgr) ;
	Manager loginManager(String email, String phone,String password, int otp) throws GlobalLoanException;
	Manager getRandomManager();
	Manager sendOtp(String email, String phone);
	boolean verifyOtp(Manager mgr, int otp);
}
