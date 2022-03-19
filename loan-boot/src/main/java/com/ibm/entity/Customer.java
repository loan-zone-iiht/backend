package com.ibm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "loan_customers_boot")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne // can have one pan no
	@JoinColumn(name = "pan_no")
	private Pan panNo;
	@OneToOne // can have one loan details at a time
	@JoinColumn(name = "loan_details_id")
	private LoanDetails loanDetailsId;
	@OneToMany(mappedBy = "custId") // can have multiple payment histories
	private List<PaymentHistory> paymentHistories = new ArrayList<PaymentHistory>();
	@Column(length = 25)
	private String name;
	@Column(length = 25)
	private String email;
	@Column(length = 15)
	private String phone;
	private double salary;
//	@Column(name = "acc_no", length = 25)
//	private String accountNo;	
//	@OneToMany(mappedBy = "custId") // can have multiple nextPayback
//	private List<NextPayback> nextPaybacks = new ArrayList<NextPayback>();

	public Customer() {
	}

	public Customer(Pan panNo, String name, String email, String phone) {
		this.panNo = panNo;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pan getPanNo() {
		return panNo;
	}

	public void setPanNo(Pan panNo) {
		this.panNo = panNo;
	}

	public LoanDetails getLoanDetailsId() {
		return loanDetailsId;
	}

	public void setLoanDetailsId(LoanDetails loanDetailsId) {
		this.loanDetailsId = loanDetailsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", panNo=" + panNo + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ "]";
	}

}
