package com.ibm.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ibm.enums.RoleOptions;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * Class {Customer} is the entity defining the
 * fields of the customer table in DB.
 * 
 * @JsonIdentityInfo handles JSON references,
 * and stops them becoming infinitely nested objects.
 * No need for JsonBackReference and JsonManagedReference anymore.
 * 
 * 
 * @author Saswata Dutta
 */


@Entity
@Table(name = "loan_customers_boot")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne // can have one pan no
	@JoinColumn(name = "pan_no")
	private Pan pan;
	@OneToOne // can have one loan details at a time
	@JoinColumn(name = "loan_detail_id")
	private LoanDetails loanDetail;
	@OneToMany(mappedBy = "customer") // can have multiple payment histories
	private List<PaymentHistory> paymentHistories = new ArrayList<PaymentHistory>();
	@Column(length = 25)
	private String name;
	@Column(unique = true, length = 25)
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(length = 50)
	private String password;
	@Column(length = 15)
	private String phone;
	private double salary;
	@Enumerated(EnumType.STRING) // only can have 2 types of values
	@Column(length = 12)
	private RoleOptions role;
//	@Column(name = "acc_no", length = 25)
//	private String accountNo;	
//	@OneToMany(mappedBy = "custId") // can have multiple nextPayback
//	private List<NextPayback> nextPaybacks = new ArrayList<NextPayback>();

	public Customer() {
	}

	public Customer(Pan pan, String name, String email, String phone) {
		this.pan = pan;
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

	public Pan getPan() {
		return pan;
	}

	public void setPan(Pan pan) {
		this.pan = pan;
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

	
	public LoanDetails getLoanDetail() {
		return loanDetail;
	}

	public void setLoanDetail(LoanDetails loanDetail) {
		this.loanDetail = loanDetail;
	}

	public List<PaymentHistory> getPaymentHistories() {
		return paymentHistories;
	}

	public void setPaymentHistories(List<PaymentHistory> paymentHistories) {
		this.paymentHistories = paymentHistories;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public RoleOptions getRole() {
		return role;
	}

	public void setRole(RoleOptions role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", panNo=" + pan + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ "]";
	}

}
