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
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ibm.enums.Dependents_option;
import com.ibm.enums.GenderOptions;
import com.ibm.enums.property_type_options;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "loan_customers_boot")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // json infy
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
//	@JsonIgnore
	@OneToOne // can have one pan no
	@JoinColumn(name = "pan_no")
	private Pan pan;
	@OneToOne // can have one loan details at a time
	@JoinColumn(name = "loan_detail_id")
	private LoanDetails loanDetail;
//	@JsonManagedReference
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
	
	@Enumerated(EnumType.STRING)
	private GenderOptions gender;
	private boolean Married;
	@Enumerated(EnumType.STRING)
	private Dependents_option dependents;
	private boolean gradStat;
	private boolean isSelfEmployed;
	private int coApplicantIncome=0;
	private property_type_options propertyType;
	
	private Integer otp;


	public GenderOptions getGender() {
		return gender;
	}

	public void setGender(GenderOptions gender) {
		this.gender = gender;
	}

	public boolean isMarried() {
		return Married;
	}

	public void setMarried(boolean married) {
		Married = married;
	}

	public Dependents_option getDependents() {
		return dependents;
	}

	public void setDependents(Dependents_option dependents) {
		this.dependents = dependents;
	}

	public boolean isGradStat() {
		return gradStat;
	}

	public void setGradStat(boolean gradStat) {
		this.gradStat = gradStat;
	}

	public boolean isSelfEmployed() {
		return isSelfEmployed;
	}

	public void setSelfEmployed(boolean isSelfEmployed) {
		this.isSelfEmployed = isSelfEmployed;
	}

	public int getCoApplicantIncome() {
		return coApplicantIncome;
	}

	public void setCoApplicantIncome(int coApplicantIncome) {
		this.coApplicantIncome = coApplicantIncome;
	}

	public property_type_options getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(property_type_options propertyType) {
		this.propertyType = propertyType;
	}

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
	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", panNo=" + pan + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ "]";
	}

}
