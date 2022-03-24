package com.ibm.pojo;

import com.ibm.enums.FromOptions;
import com.ibm.enums.PaymentMethod;
import com.ibm.enums.PaymentType;
import com.ibm.enums.SuccessType;

public class PaymentTransaction {
	private int loanId;
	private FromOptions fromOptions;
	private PaymentMethod paymentMethod;
	private PaymentType paymentType;
	private SuccessType successType;
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public FromOptions getFromOptions() {
		return fromOptions;
	}
	public void setFromOptions(FromOptions fromOptions) {
		this.fromOptions = fromOptions;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public SuccessType getSuccessType() {
		return successType;
	}
	public void setSuccessType(SuccessType successType) {
		this.successType = successType;
	}

	
}
