package com.ibm.service;

import com.ibm.entity.Manager;
import com.ibm.exception.GlobalLoanException;
/**
 * Class {ManagerService} is a service interface
 * for manager entity, which uses the methods from
 * manager repository.
 * 
 * @author Saswata Dutta
 */
public interface ManagerService {
	Manager createManager(Manager mgr) ;
	Manager loginManager(String email, String phone,String password,int otp) throws GlobalLoanException;
	Manager getRandomManager();
	Manager sendOtp(String email, String phone);
	boolean verifyOtp(Manager mgr, int otp);
}
