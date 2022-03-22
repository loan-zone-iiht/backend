package com.ibm.pojo;

import com.ibm.enums.StatusType;

public class UpdateLoanDetailsByStatus {
	private StatusType status;
	int loanId;
	int custId;
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
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	
}
