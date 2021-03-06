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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ibm.enums.FromOptions;
import com.ibm.enums.PaymentMethod;
import com.ibm.enums.PaymentType;
import com.ibm.enums.SuccessType;
/**
 * Class {PaymentHistory} is the entity defining the
 * fields of the table in DB which contains all the transactional
 * history.
 * 
 * @JsonIdentityInfo handles JSON references,
 * and stops them becoming infinitely nested objects.
 * No need for JsonBackReference and JsonManagedReference anymore.
 * 
 * @author Saswata Dutta
 */
@Entity
@Table(name = "loan_payment_history_boot")
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, property = "id") // json infy
public class PaymentHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "cust_id")
	@JsonIdentityReference(alwaysAsId = true)
	private Customer customer;
//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "loan_details_id")
	@JsonIdentityReference(alwaysAsId = true)
	private LoanDetails loanDetails;
	@Column(name = "payment_amount")
	private double paymentAmount;
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	@Enumerated(EnumType.STRING) // only can have 2 types of values
	@Column(name = "payment_from", length = 25)
	private FromOptions paymentFrom;
	@Enumerated(EnumType.STRING) // only can have 4 types of values
	@Column(name = "payment_method", length = 25)
	private PaymentMethod paymentMethod;
	@Enumerated(EnumType.STRING) // only can have 3 types of values
	@Column(name = "payment_type", length = 25)
	private PaymentType paymentType;

	@Enumerated(EnumType.STRING) // only can have 2 types of values
	@Column(name = "success_type", length = 25)
	private SuccessType successType;
//	@OneToOne // can have one nextpayback association
//	@JoinColumn(name = "next_payback_id")
//	private NextPayback nextPaybackId;

	public PaymentHistory() {
	}

	public PaymentHistory(Customer customer, LoanDetails loanDetails, double paymentAmount, LocalDate paymentDate,
			FromOptions paymentFrom, PaymentMethod paymentMethod, PaymentType paymentType) {
		this.customer = customer;
		this.loanDetails = loanDetails;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LoanDetails getLoanDetails() {
		return loanDetails;
	}

	public void setLoanDetails(LoanDetails loanDetails) {
		this.loanDetails = loanDetails;
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

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	
	
	
	public SuccessType getSuccessType() {
		return successType;
	}

	public void setSuccessType(SuccessType successType) {
		this.successType = successType;
	}

	@Override
	public String toString() {
		return "PaymentHistory [id=" + id + ", paymentAmount=" + paymentAmount + ", paymentDate=" + paymentDate
				+ ", paymentFrom=" + paymentFrom + ", paymentMethod=" + paymentMethod + ", paymentType=" + paymentType
				+ "]";
	}

}
