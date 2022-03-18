package com.ibm.entity;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

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

enum StatusType {
	ACCEPTED, REJECTED, PENDING;
}

@Entity
@Table(name = "loan_loan_details_boot")
public class LoanDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	@OneToOne
	@JoinColumn(name = "cust_id")
	private Customer custId;
	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Manager mgrId;
	@Column(name = "loan_principal")
	private double loanPrincipal;
	@Column(name = "loan_tenure")
	private double loanTenure;
	@Column(name = "loan_interest_rate")
	private double loanInterestRate;
	@Column(name = "loan_frequency")
	private double loanFrequency;
	@Column(name = "application_date")
	private LocalDate applicationDate;
	@Column(name = "date_begin")
	private LocalDate dateBegin;
	@Column(name = "date_end")
	private LocalDate dateEnd;
	@Column(name = "next_payback_date")
	private LocalDate nextPaybackDate;
	@Column(name = "bank_to_cust_payout")
	private boolean bankToCustPayout;
	@Column(name = "outstanding_principal")
	private double outstandingPrincipal;
	@Enumerated(EnumType.STRING)
	@Column(name = "loan_status")
	private StatusType loanStatus;

	public LoanDetails() {
	}

	public LoanDetails(Customer custId, Manager mgrId, double loanPrincipal, double loanTenure,
			double loanInterestRate, double loanFrequency, LocalDate applicationDate, LocalDate dateBegin,
			LocalDate dateEnd, LocalDate nextPaybackDate, boolean bankToCustPayout, double outstandingPrincipal,
			StatusType loanStatus) {
		this.custId = custId;
		this.mgrId = mgrId;
		this.loanPrincipal = loanPrincipal;
		this.loanTenure = loanTenure;
		this.loanInterestRate = loanInterestRate;
		this.loanFrequency = loanFrequency;
		this.applicationDate = applicationDate;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.nextPaybackDate = nextPaybackDate;
		this.bankToCustPayout = bankToCustPayout;
		this.loanStatus = loanStatus;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public Customer getCustId() {
		return custId;
	}

	public void setCustId(Customer custId) {
		this.custId = custId;
	}

	public Manager getMgrId() {
		return mgrId;
	}

	public void setMgrId(Manager mgrId) {
		this.mgrId = mgrId;
	}

	public double getLoanPrincipal() {
		return loanPrincipal;
	}

	public void setLoanPrincipal(double loanPrincipal) {
		this.loanPrincipal = loanPrincipal;
	}

	public double getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(double loanTenure) {
		this.loanTenure = loanTenure;
	}

	public double getLoanInterestRate() {
		return loanInterestRate;
	}

	public void setLoanInterestRate(double loanInterestRate) {
		this.loanInterestRate = loanInterestRate;
	}

	public double getLoanFrequency() {
		return loanFrequency;
	}

	public void setLoanFrequency(double loanFrequency) {
		this.loanFrequency = loanFrequency;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}

	public LocalDate getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(LocalDate dateBegin) {
		this.dateBegin = dateBegin;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

	public LocalDate getNextPaybackDate() {
		return nextPaybackDate;
	}

	public void setNextPaybackDate(LocalDate nextPaybackDate) {
		this.nextPaybackDate = nextPaybackDate;
	}

	public boolean isBankToCustPayout() {
		return bankToCustPayout;
	}

	public void setBankToCustPayout(boolean bankToCustPayout) {
		this.bankToCustPayout = bankToCustPayout;
	}

	public double getOutstandingPrincipal() {
		return outstandingPrincipal;
	}

	public void setOutstandingPrincipal(double outstandingPrincipal) {
		this.outstandingPrincipal = outstandingPrincipal;
	}

	public StatusType getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(StatusType loanStatus) {
		this.loanStatus = loanStatus;
	}

	@Override
	public String toString() {
		return "LoanDetails [loanId=" + loanId + ", loanPrincipal=" + loanPrincipal + ", loanTenure=" + loanTenure
				+ ", loanInterestRate=" + loanInterestRate + ", loanFrequency=" + loanFrequency + ", applicationDate="
				+ applicationDate + ", dateBegin=" + dateBegin + ", dateEnd=" + dateEnd + ", nextPaybackDate="
				+ nextPaybackDate + ", bankToCustPayout=" + bankToCustPayout + ", outstandingPrincipal="
				+ outstandingPrincipal + ", loanStatus=" + loanStatus + "]";
	}

}
