package com.ibm.service;

import java.util.List;

import com.ibm.entity.Customer;
import com.ibm.entity.Pan;

public interface PanService {
	Pan createPan(Pan p);
	Pan updatePan(Pan p);
	Pan getPanByPanNo(String panNo);
	Pan getPanByCustomerId(int custId);
	int getCibilScore(int custId);
	List<Pan> getAllPans();
}
