package com.ibm.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Customer;
import com.ibm.entity.LoanDetails;
import com.ibm.entity.Manager;
import com.ibm.entity.PaymentHistory;
import com.ibm.enums.FromOptions;
import com.ibm.enums.PaymentType;
import com.ibm.enums.StatusType;
import com.ibm.exception.GlobalLoanException;
import com.ibm.pojo.PaymentDetail;
import com.ibm.pojo.PaymentTransaction;
import com.ibm.repo.CustomerRepository;
import com.ibm.repo.LoanDetailsRepository;
import com.ibm.repo.ManagerRepository;

@Service
public class LoanDetailsServiceImpl implements LoanDetailsService {

	@Autowired
	private LoanDetailsRepository loanDetailsRepo;
	@Autowired
	private CustomerService custService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private PaymentHistoryService paymneHistoryService;

	// functional interface used to calc payments
	Function<LoanDetails, PaymentDetail> paymentDetailCalculator = (ld) -> {

		double rate = ld.getLoanInterestRate() / 100 / (12 / ld.getLoanFrequency());
		int numberOfPayments = (int) (ld.getLoanTenure() * (12 / ld.getLoanFrequency()));

		double paymentAmount = (rate * ld.getLoanPrincipal()) / (1 - Math.pow(1 + rate, -numberOfPayments));
		double paymentAmountRounded = Math.round(paymentAmount * 100.0) / 100.0;
		return new PaymentDetail(paymentAmountRounded, numberOfPayments);
	};

	@Override
	public LoanDetails createLoanDetails(LoanDetails ld, int custId) {
		Customer cust = custService.getCustomerById(custId);
		StatusType statusRej = Enum.valueOf(StatusType.class, "REJECTED");
		StatusType status2Comp = Enum.valueOf(StatusType.class, "COMPLETED");
		if (cust.getLoanDetail() == null || cust.getLoanDetail().getLoanStatus() == statusRej
				|| cust.getLoanDetail().getLoanStatus() == status2Comp) {
			// can add loanDetail if there's no ongoing loan

			// setting customer to the loanDetail
			ld.setCustomer(cust);
			ld.setApplicationDate(LocalDate.now());
			ld.setLoanStatus(StatusType.PENDING);

			// setting loanDetail to the customer
			cust.setLoanDetail(ld);

			// setting (random)manager to loanDetails
			Manager mgr = managerService.getRandomManager();
			ld.setManager(mgr);

			// saving
			loanDetailsRepo.save(ld); // save the new obj (loanDetails) before the customer
			custService.updateCustomer(cust);
			return ld;
		} else {
			throw new GlobalLoanException("409", "Customer already has an ongoing loan");
		}
	}

	@Override
	public List<LoanDetails> getAllLoanDetails() {
		return loanDetailsRepo.findAll();
	}

	@Override
	public List<LoanDetails> getLoandetailsByStatus(StatusType status) {
		return loanDetailsRepo.findAllByLoanStatus(status);
	}

	@Override
	public LoanDetails updateLoanStatusFromLoanId(int loanId, StatusType status) {
		LoanDetails ld = loanDetailsRepo.findById(loanId).get();
		// logic for every status
		if (status == status.ACCEPTED) {
			PaymentDetail pd = paymentDetailCalculator.apply(ld);
			ld.setPaymentAmount(pd.getPaymentAmount());
			ld.setNoOfPayments(pd.getNumberOfPayments());
			ld.setDateBegin(LocalDate.now());
			// setting next payment date
			
			/******************
			 * Next payment date
			 * *******************/
		}else if(status == status.FORECLOSURE_ACCEPTED) {
			ld.setPaymentAmount(ld.getPaymentAmount()*ld.getNoOfPayments());
			ld.setNoOfPayments(1);
		}
		ld.setLoanStatus(status);
		loanDetailsRepo.save(ld);
		return ld;
	}
	
	@Override
	public LoanDetails applyForForeclosure(int loanId) {
		LoanDetails ld = this.getLoanDetailsByLoanId(loanId);
		ld.setPaymentAmount(ld.getNoOfPayments()*ld.getPaymentAmount());
		ld.setLoanStatus(StatusType.FORECLOSURE_PENDING);
		loanDetailsRepo.save(ld);
		return ld;
	}

	@Override
	public LoanDetails updateBankToCustPayout(int loanId, boolean payout) {
		LoanDetails ld = loanDetailsRepo.findById(loanId).get();
		ld.setBankToCustPayout(payout);
		loanDetailsRepo.save(ld);
		return ld;
	}

	@Override
	public PaymentHistory payBack(PaymentTransaction pt) {
		LoanDetails ld = this.getLoanDetailsByLoanId(pt.getLoanId());
		double oldPaymentAmount = ld.getPaymentAmount();
		if (ld.getLoanStatus() == StatusType.ACCEPTED) {

			// creating new pay-history
			PaymentHistory ph = new PaymentHistory();

			// setting values;
			ph.setLoanDetails(ld);
			ph.setCustomer(ld.getCustomer());
			ph.setPaymentAmount(oldPaymentAmount);
			ph.setPaymentDate(LocalDate.now());
			ph.setPaymentFrom(pt.getFromOptions());
			ph.setPaymentMethod(pt.getPaymentMethod());
			ph.setPaymentType(PaymentType.REGULAR);
			ph.setSuccessType(pt.getSuccessType());
			// Decrease count by 1
			ld.setNoOfPayments(ld.getNoOfPayments() - 1);

			// condition for last payment
			if (ld.getNoOfPayments() <= 0) {
				ld.setLoanStatus(StatusType.COMPLETED);
				ld.setPaymentAmount(0);
				ld.setDateEnd(LocalDate.now());
			}

			// saving the history
			paymneHistoryService.createPaymentHistory(ph);
			return ph;
		} else {
			throw new GlobalLoanException("404", "No ongoing loan with this id");
		}

	}

	@Override
	public PaymentHistory downpayment(PaymentTransaction pt) {
		LoanDetails ld = this.getLoanDetailsByLoanId(pt.getLoanId());
		double oldPaymentAmount = ld.getPaymentAmount();
		// no of payments needed should be more than 1 and less than total no of payments left
		if (ld.getNoOfPayments() >= pt.getNoOfPayments() && pt.getNoOfPayments()>=1) {
			ld.setNoOfPayments(ld.getNoOfPayments() - pt.getNoOfPayments()); // payments left

			if (ld.getNoOfPayments() == 0) { // if last payment happens
				ld.setLoanStatus(StatusType.COMPLETED);
				ld.setPaymentAmount(0);
				ld.setDateEnd(LocalDate.now());
			}

			// creating history
			PaymentHistory ph = new PaymentHistory();

			// setting values;
			ph.setLoanDetails(ld);
			ph.setCustomer(ld.getCustomer());
			ph.setPaymentAmount(oldPaymentAmount * pt.getNoOfPayments()); // total amount
			ph.setPaymentDate(LocalDate.now());
			ph.setPaymentFrom(pt.getFromOptions());
			ph.setPaymentMethod(pt.getPaymentMethod());
			ph.setPaymentType(PaymentType.DOWNPAYMENT);
			ph.setSuccessType(pt.getSuccessType());
			// saving the history
			paymneHistoryService.createPaymentHistory(ph);

			return ph;
		} else {
			throw new GlobalLoanException("404", "send proper number of payments (more than 1 and less than total");
		}
	}
	
	@Override
	public PaymentHistory foreclosurePayment(PaymentTransaction pt) {
		LoanDetails ld = this.getLoanDetailsByLoanId(pt.getLoanId());
		double oldPaymentAmount = ld.getPaymentAmount();
			if(ld.getLoanStatus()==StatusType.FORECLOSURE_ACCEPTED) {
				ld.setNoOfPayments(0);
				ld.setDateEnd(LocalDate.now());
				ld.setPaymentAmount(0);
				ld.setLoanStatus(StatusType.COMPLETED);

				// creating history
				PaymentHistory ph = new PaymentHistory();

				// setting values;
				ph.setLoanDetails(ld);
				ph.setCustomer(ld.getCustomer());
				ph.setPaymentAmount(oldPaymentAmount);
				ph.setPaymentDate(LocalDate.now());
				ph.setPaymentFrom(pt.getFromOptions());
				ph.setPaymentMethod(pt.getPaymentMethod());
				ph.setPaymentType(PaymentType.FORECLOSURE);
				ph.setSuccessType(pt.getSuccessType());
				// saving the history
				paymneHistoryService.createPaymentHistory(ph);

				return ph;

			}else {
				throw new GlobalLoanException("404", "Not accepted for foreclosure");
			}
	}

	@Override
	public LoanDetails getLoanDetailsByLoanId(int loanId) {
		return loanDetailsRepo.findById(loanId)
				.orElseThrow(() -> new GlobalLoanException("404", "LoanDetails id not found"));
	}

	/******************************
	 * DONT USE BELOW METHODS
	 ***************************************/

//	@Override
//	public double getOutstandingPrincipal(int loanId) {
//		LoanDetails ld = loanDetailsRepo.findById(loanId).get();
//		return ld.getOutstandingPrincipal();
//	}
//
//	@Override
//	public void updateOutstandingPrincipal(int loanId, double op) {
//		LoanDetails ld = loanDetailsRepo.findById(loanId).get();
//		ld.setOutstandingPrincipal(op);
//		loanDetailsRepo.save(ld);
//	}

	// DONT USE
	@Override
	public double getPaymentAmount(int loanId) {
		LoanDetails ld = loanDetailsRepo.findById(loanId).get();

		double rate = ld.getLoanInterestRate() / 100 / (12 / ld.getLoanFrequency());
		int numberOfPayments = (int) (ld.getLoanTenure() * (12 / ld.getLoanFrequency()));

		double paymentAmount = (rate * ld.getLoanPrincipal()) / (1 - Math.pow(1 + rate, -numberOfPayments));

		return Math.round(paymentAmount);

//		LoanDetails ld = loanDetailsRepo.findById(loanId).get();
//		double EAR = Math.pow((1 + ld.getLoanInterestRate() / 100 / 12), 12) - 1;
//		double pT = 12 / ld.getLoanFrequency();
//		double nominalRate = pT * (Math.pow((1 + EAR), 1 / pT) - 1);
//		int numberOfPeriod = (int) ((ld.getLoanTenure() * 12) / ld.getLoanFrequency());
//		double rate = nominalRate / pT;
//		double equatedInstallment = (ld.getOutstandingPrincipal() * rate * Math.pow(1 + rate, numberOfPeriod))
//				/ (Math.pow(1 + rate, numberOfPeriod) - 1);
//
//		System.err.println("EAR: " + EAR + " nominalRate: " + nominalRate + " numberOfPeriod: " + numberOfPeriod
//				+ " rate: " + rate + " equatedInstallment: " + equatedInstallment);
//		
//		
////		int newOutstandingPrinciple = (equatedInstallment + Math.pow(1 + rate , x))
//		
//		return equatedInstallment;

//		int numberOfPeriod = ld.getLoanTenure();
//		return 0;
	}

	@Override
	public LoanDetails updateLoanDetails(LoanDetails ld) {
		return loanDetailsRepo.save(ld);
	}

}
