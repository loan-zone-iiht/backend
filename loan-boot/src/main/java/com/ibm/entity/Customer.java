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
@Table(name = "loan_customers_boot")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "acc_no", length = 25)
	private String accountNo;	
	@Column(length = 25)
	private String name;
	@Column(length = 25)
	private String email;
	@Column(length = 15)
	private String phone;
	@OneToOne
	@JoinColumn(name = "pan_no")
	private Pan panNo;
	
	public Customer() {
	}
	
	public Customer(int id, String accountNo, String name, String email, String phone, Pan panNo) {
		this.id = id;
		this.accountNo = accountNo;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.panNo = panNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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

	public Pan getPanNo() {
		return panNo;
	}

	public void setPanNo(Pan panNo) {
		this.panNo = panNo;
	}
	
	
	
	
}
