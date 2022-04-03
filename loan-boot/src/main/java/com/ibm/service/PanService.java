package com.ibm.service;

import java.util.List;

import com.ibm.entity.Pan;
import com.ibm.exception.GlobalLoanException;

public interface PanService {
	Pan createPan(Pan p);
	Pan updatePan(Pan p) throws GlobalLoanException;
	Pan getPanByPanNo(String panNo) throws GlobalLoanException;
	Pan getPanByCustomerId(int custId) throws GlobalLoanException;
	int getCibilScore(int custId) throws GlobalLoanException;
	List<Pan> getAllPans();
}
