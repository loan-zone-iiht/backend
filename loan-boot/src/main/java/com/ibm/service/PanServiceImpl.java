package com.ibm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Customer;
import com.ibm.entity.Pan;
import com.ibm.repo.CustomerRepository;
import com.ibm.repo.PanRepository;

@Service
public class PanServiceImpl implements PanService {
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private PanRepository panRepo;

	@Override
	public Pan createPan(Pan p) {
		return panRepo.save(p);
	}

	@Override
	public Pan updatePan(Pan p) {
		return panRepo.save(p);
	}

	@Override
	public Pan getPanByPanNo(String panNo) {
		return panRepo.findById(panNo).get();
	}

	@Override
	public Pan getPanByCustomerId(int custId) {
		Customer cust = customerRepo.findById(custId).get();
		return panRepo.findByCustomer(cust);
	}

	@Override
	public int getCibilScore(int custId) {
		Customer cust = customerRepo.findById(custId).get();
		return panRepo.getCibilScore(cust);
	}

}
