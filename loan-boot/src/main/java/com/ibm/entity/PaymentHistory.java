package com.ibm.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

enum FromOptions {
	BANK, CUSTOMER;
}

enum PaymentMethod {
	UPI, CREDIT_CARD, DEBIT_CARD, BANK_TRANSFER;
}

enum PaymentType {
	REGULAR, DOWNPAYMENT, FORECLOSURE;
}

@Entity
@Table(name = "loan_payment_history_boot")
public class PaymentHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private Customer custId;
	@ManyToOne
	@JoinColumn(name = "loan_details_id")
	private LoanDetails loanDetailsId;
	@Column(name = "payment_amount")
	private double paymentAmount;
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	@Enumerated(EnumType.STRING) // only can have 2 types of values
	@Column(name = "payment_from", length = 25)
	private FromOptions paymentFrom;
	@Enumerated(EnumType.STRING) // only can have 4 types of values
	@Column(name = "payment_method", length = 25)
	private FromOptions paymentMethod;
	@Enumerated(EnumType.STRING) // only can have 3 types of values
	@Column(name = "payment_type", length = 25)
	private FromOptions paymentType;
//	@OneToOne // can have one nextpayback association
//	@JoinColumn(name = "next_payback_id")
//	private NextPayback nextPaybackId;

	public PaymentHistory() {
	}

	public PaymentHistory(int id, Customer custId, LoanDetails loanDetailsId, double paymentAmount,
			LocalDate paymentDate, FromOptions paymentFrom, FromOptions paymentMethod, FromOptions paymentType) {
		this.id = id;
		this.custId = custId;
		this.loanDetailsId = loanDetailsId;
		this.paymentAmount = paymentAmount;
		this.paymentDate = paymentDate;
		this.paymentFrom = paymentFrom;
		this.paymentMethod = paymentMethod;
		this.paymentType = paymentType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustId() {
		return custId;
	}

	public void setCustId(Customer custId) {
		this.custId = custId;
	}

	public LoanDetails getLoanDetailsId() {
		return loanDetailsId;
	}

	public void setLoanDetailsId(LoanDetails loanDetailsId) {
		this.loanDetailsId = loanDetailsId;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public FromOptions getPaymentFrom() {
		return paymentFrom;
	}

	public void setPaymentFrom(FromOptions paymentFrom) {
		this.paymentFrom = paymentFrom;
	}

	public FromOptions getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(FromOptions paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public FromOptions getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(FromOptions paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public String toString() {
		return "PaymentHistory [id=" + id + ", paymentAmount=" + paymentAmount + ", paymentDate=" + paymentDate
				+ ", paymentFrom=" + paymentFrom + ", paymentMethod=" + paymentMethod + ", paymentType=" + paymentType
				+ "]";
	}

}
