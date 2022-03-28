package com.ibm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "loan_pan_boot")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "panNo") // json infy
public class Pan {
	@Id
	private String panNo;
	@OneToOne
	@JoinColumn(name = "cust_id")
	private Customer customer;
	@Column(name = "cibil_score")
	private int cibilScore;

	public Pan() {
	}

	public Pan(String panNo, int cibilScore) {
		this.panNo = panNo;
		this.cibilScore = cibilScore;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public int getCibilScore() {
		return cibilScore;
	}

	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Pan [no=" + panNo + ", cibilScore=" + cibilScore + "]";
	}

}
