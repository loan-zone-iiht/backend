package com.ibm.pojo;

import com.ibm.enums.StatusType;
/**
 * Class {UpdateLoanDetailsByStatus} is a simple POJO
 * to handle the incoming request object 
 * to update the current status of loan
 * 
 * 
 * @author Saswata Dutta
 */
public class UpdateLoanDetailsByStatus {
	private StatusType status;
	int loanId;
	public StatusType getStatus() {
		return status;
	}
	public void setStatus(StatusType status) {
		this.status = status;
	}
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	
}
