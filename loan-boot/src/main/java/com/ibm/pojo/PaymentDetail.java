package com.ibm.pojo;

public class PaymentDetail {
	double paymentAmount;
	int numberOfPayments;

	public PaymentDetail(double paymentAmount, int numberOfPayments) {
		this.paymentAmount = paymentAmount;
		this.numberOfPayments = numberOfPayments;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public int getNumberOfPayments() {
		return numberOfPayments;
	}

	public void setNumberOfPayments(int numberOfPayments) {
		this.numberOfPayments = numberOfPayments;
	}

}
