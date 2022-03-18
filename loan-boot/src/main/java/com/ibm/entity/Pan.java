package com.ibm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "loan_pan_boot")
public class Pan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	@OneToOne
	@JoinColumn(name = "cust_id")
	private Customer custId;
	@Column(name = "cibil_score")
	private int cibilScore;	
	
	public Pan() {
	}

	public Pan(int no, int cibilScore, Customer custId) {
		this.no = no;
		this.cibilScore = cibilScore;
		this.custId = custId;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCibilScore() {
		return cibilScore;
	}

	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
	}

	public Customer getCustId() {
		return custId;
	}

	public void setCustId(Customer custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "Pan [no=" + no + ", cibilScore=" + cibilScore + "]";
	}
	
	
	
	
}
