package com.ibm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "loan_managers_boot")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // json infy
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	@JsonManagedReference
	@OneToMany(mappedBy = "manager")
	private List<LoanDetails> loanDetails = new ArrayList<LoanDetails>();
	@Column(length = 25)
	private String name;
	@Column(unique = true, length = 25)
	private String email;
	@Column(length = 15)
	private String phone;
	@Column(length = 50)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private Integer otp;
	public Manager() {
	}

	public Manager(String name, String email, String phone) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<LoanDetails> getLoanDetails() {
		return loanDetails;
	}

	public void setLoanDetails(List<LoanDetails> loanDetails) {
		this.loanDetails = loanDetails;
	}
	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}

}
