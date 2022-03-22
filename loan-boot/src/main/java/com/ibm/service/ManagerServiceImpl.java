package com.ibm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Manager;
import com.ibm.exception.GlobalLoanException;
import com.ibm.repo.ManagerRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerRepository managerRepo;

	@Override
	public Manager createManager(Manager mgr) throws GlobalLoanException{
		return managerRepo.save(mgr);
	}

	@Override
	public Manager loginManager(String email, String phone, String password) throws GlobalLoanException {
		Manager mgr = managerRepo.loginManager(email, phone);
		System.err.println(mgr);
		if(mgr == null) {
			throw new GlobalLoanException("404", "No manager with this credential");
		}else if (mgr.getPassword().equals(password)) {
			return mgr;
		} else {
			throw new GlobalLoanException("403", "Wrong password");
		}
	}

	@Override
	public Manager getRandomManager() {
		return managerRepo.getRandomManager();
	}

}
