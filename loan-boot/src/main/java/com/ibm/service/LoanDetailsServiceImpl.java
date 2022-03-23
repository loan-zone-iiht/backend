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
import com.ibm.enums.StatusType;
import com.ibm.exception.GlobalLoanException;
import com.ibm.pojo.PaymentDetail;
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
		}
		ld.setLoanStatus(status);
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

	@Override
	public LoanDetails getLoanDetailsByLoanId(int loanId) {
		return loanDetailsRepo.findById(loanId)
				.orElseThrow(() -> new GlobalLoanException("404", "LoanDetails id not found"));
	}

}
