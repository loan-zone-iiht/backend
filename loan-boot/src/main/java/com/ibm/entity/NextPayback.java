package com.ibm.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class NextPayback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private Customer custId;
	@ManyToOne
	@JoinColumn(name = "loan_details_id")
	private LoanDetails loanDetailsId;
	@Column(name = "date_created")
	private LocalDate dateCreated;	
	@Column(name = "payback_amount")	
	private double paybackAmount;
	
	public NextPayback() {
	}
	
	public NextPayback(Customer custId, LoanDetails loanDetailsId, LocalDate dateCreated, double paybackAmount) {
		this.custId = custId;
		this.loanDetailsId = loanDetailsId;
		this.dateCreated = dateCreated;
		this.paybackAmount = paybackAmount;
	}
	
	
	
}
