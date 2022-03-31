package com.ibm.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.email.MailSender;
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

/**
 * Class {LoanDetailsServiceImpl} is a service class extending
 * {LoanDetailsService} for loan details entity, which uses the methods from
 * loan details repository.
 * 
 * @author Saswata Dutta
 */

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

	@Autowired
	private MailSender mailSender;

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

			// sending mail confirming loan application
			String mailContent = "We've received your application for a loan of amount " + ld.getLoanPrincipal()
					+ " for a total of " + (int) (ld.getLoanTenure() * 12) + " months, with a rate of "
					+ ld.getLoanInterestRate()
					+ "%. Hang tight until we review the application. We'll get back to you soon. Cheers!!";

			mailSender.setEmailSubject("Thanks for applying for loan.");
			mailSender.setReceiverEmail(cust.getEmail());
			mailSender.setEmailContent(mailContent);
			mailSender.sendEmail();

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

	// Update the status of the loans via the manager only
	@Override
	public LoanDetails updateLoanStatusFromLoanId(int loanId, StatusType status) {
		LoanDetails ld = loanDetailsRepo.findById(loanId).get();
		// logic for every status
		if (status == status.ACCEPTED) {
			PaymentDetail pd = paymentDetailCalculator.apply(ld);
			ld.setPaymentAmount(pd.getPaymentAmount());
			ld.setNoOfPayments(pd.getNumberOfPayments());
			ld.setDateBegin(LocalDate.now());

			// sending mail confirming loan application
			String mailContent = "Your loan application for amount. " + ld.getLoanPrincipal()
					+ " has been accepted, your payback amount is " + ld.getPaymentAmount()
					+ " Which you need to pay regularly.";

			mailSender.setEmailSubject("Congratulations, your loan application has been accepted.");
			mailSender.setReceiverEmail(ld.getCustomer().getEmail());
			mailSender.setEmailContent(mailContent);
			mailSender.sendEmail();

			// setting next payment date

			/******************
			 * Next payment date
			 *******************/
		} else if (status == status.FORECLOSURE_ACCEPTED) {
			ld.setPaymentAmount(ld.getPaymentAmount() * ld.getNoOfPayments());
			ld.setNoOfPayments(1); // forecloure means customer pays out all remaining loans at once

			// sending mail confirming foreclosure acceptence
			String mailContent = "Your application for foreclosure has been accepted. You need to pay a total of "
					+ ld.getPaymentAmount() + " as your final payment.";

			mailSender.setEmailSubject("Congratulations, foreclosure application has been accepted.");
			mailSender.setReceiverEmail(ld.getCustomer().getEmail());
			mailSender.setEmailContent(mailContent);
			mailSender.sendEmail();
		}
		ld.setLoanStatus(status);
		loanDetailsRepo.save(ld);
		return ld;
	}

	// apply for foreclosure via customer
	@Override
	public LoanDetails applyForForeclosure(int loanId) {
		LoanDetails ld = this.getLoanDetailsByLoanId(loanId);
		ld.setPaymentAmount(ld.getNoOfPayments() * ld.getPaymentAmount());
		ld.setLoanStatus(StatusType.FORECLOSURE_PENDING);
		loanDetailsRepo.save(ld);

		// sending mail confirming foreclosure application
		String mailContent = "We've received your application for foreclosure, Hang tight until we review the application. We'll get back to you soon. Cheers!!";

		mailSender.setEmailSubject("Thanks for applying for foreclosure.");
		mailSender.setReceiverEmail(ld.getCustomer().getEmail());
		mailSender.setEmailContent(mailContent);
		mailSender.sendEmail();
		return ld;
	}

	// specify if bank has given money to customer or not
	// if payout is true, it means bank has sent money to customer.
	@Override
	public LoanDetails updateBankToCustPayout(int loanId, boolean payout) {
		LoanDetails ld = loanDetailsRepo.findById(loanId).get();
		ld.setBankToCustPayout(payout);
		loanDetailsRepo.save(ld);

		// sending mail confirming that bank paid the customer
		String mailContent = "We've delivered the total amount of Rs " + ld.getLoanPrincipal()
				+ " to your bank account. In case you didn't recived it, please mail us at "
				+ ld.getCustomer().getEmail() + ".";

		mailSender.setEmailSubject("Congratulations, we've deliverd the Rs " + ld.getLoanPrincipal() + " to you.");
		mailSender.setReceiverEmail(ld.getCustomer().getEmail());
		mailSender.setEmailContent(mailContent);
		mailSender.sendEmail();
		return ld;
	}

	/**
	 * Method {payBack} creates a single payment history, for a customer's regular
	 * payment. We can pay the monthly/quaterly/yearly or any time gap via the
	 * customer
	 * 
	 * @author Saswata Dutta
	 */
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

				// sending mail confirming of getting final payment via payback
				String mailContent = "We've recieved an amount of Rs " + oldPaymentAmount + " via "
						+ pt.getPaymentMethod()
						+ " payment. This was your final payment, Thanks for paying us back. Your loan payback is now completed";

				mailSender.setEmailSubject("Congratulations, you've paid back your complete loan.");
				mailSender.setReceiverEmail(ld.getCustomer().getEmail());
				mailSender.setEmailContent(mailContent);
				mailSender.sendEmail();
			}

			// saving the history
			paymneHistoryService.createPaymentHistory(ph);

			// sending success mail of receiving regular payback.
			String mailContent = "We've received your payback of Rs " + ld.getPaymentAmount() + " via "
					+ pt.getPaymentMethod() + " payment.";

			mailSender.setEmailSubject("Thanks for paying your due amount.");
			mailSender.setReceiverEmail(ld.getCustomer().getEmail());
			mailSender.setEmailContent(mailContent);
			mailSender.sendEmail();

			return ph;
		} else {
			throw new GlobalLoanException("404", "No ongoing loan with this id");
		}

	}

	/**
	 * Method {downpayment} is needed for a customer to make a downpayment of 1 or
	 * more than 1 reguler paybacks.
	 * 
	 * we can give advance downpayment of the loan payback via the customer.
	 * 
	 * @author Saswata Dutta
	 */
	@Override
	public PaymentHistory downpayment(PaymentTransaction pt) {
		LoanDetails ld = this.getLoanDetailsByLoanId(pt.getLoanId());
		double oldPaymentAmount = ld.getPaymentAmount();
		/**
		 * Here, no of payments needed, should be more than 1 and less than total no of
		 * payments left.
		 */
		if (ld.getNoOfPayments() >= pt.getNoOfPayments() && pt.getNoOfPayments() >= 1) {
			ld.setNoOfPayments(ld.getNoOfPayments() - pt.getNoOfPayments()); // payments left

			/**
			 * If the downpayment includes the last possible loan payback in that case we
			 * change the PaymentAmount to 0. To create the DB entry for the transaction
			 * we'll need the, real/old PaymentAmount. So, we're keeping that.
			 */
			if (ld.getNoOfPayments() == 0) { // if last payment happens
				ld.setLoanStatus(StatusType.COMPLETED);
				ld.setPaymentAmount(0);
				ld.setDateEnd(LocalDate.now());
				// sending mail confirming of getting final payment via downpayment
				String mailContent = "We've recieved an amount of Rs " + oldPaymentAmount + " via "
						+ pt.getPaymentMethod()
						+ " payment. This was your final payment, Thanks for paying us back. Your loan payback is now completed";

				mailSender.setEmailSubject("Congratulations, you've paid back your complete loan.");
				mailSender.setReceiverEmail(ld.getCustomer().getEmail());
				mailSender.setEmailContent(mailContent);
				mailSender.sendEmail();
			}

			// creating history
			PaymentHistory ph = new PaymentHistory();

			// setting values;
			ph.setLoanDetails(ld);
			ph.setCustomer(ld.getCustomer());
			ph.setPaymentAmount(oldPaymentAmount * pt.getNoOfPayments());
			ph.setPaymentDate(LocalDate.now());
			ph.setPaymentFrom(pt.getFromOptions());
			ph.setPaymentMethod(pt.getPaymentMethod());
			ph.setPaymentType(PaymentType.DOWNPAYMENT);
			ph.setSuccessType(pt.getSuccessType());
			// saving the history
			paymneHistoryService.createPaymentHistory(ph);

			// saving the history
			paymneHistoryService.createPaymentHistory(ph);

			// sending success mail of receiving downpayment.
			String mailContent = "We've received your downpayment of Rs " + ld.getPaymentAmount() + " via "
					+ pt.getPaymentMethod() + " payment. Which is a total of " + pt.getNoOfPayments()
					+ " regular payments.";

			mailSender.setEmailSubject("Thanks for paying the downpayment.");
			mailSender.setReceiverEmail(ld.getCustomer().getEmail());
			mailSender.setEmailContent(mailContent);
			mailSender.sendEmail();

			return ph;
		} else {
			throw new GlobalLoanException("404", "send proper number of payments (more than 1 and less than total");
		}
	}

	/**
	 * Method {foreclosurePayment} is needed for a customer to make a payment for
	 * their final foreclosure amount.
	 * 
	 * @author Saswata Dutta
	 */
	@Override
	public PaymentHistory foreclosurePayment(PaymentTransaction pt) {
		LoanDetails ld = this.getLoanDetailsByLoanId(pt.getLoanId());
		double oldPaymentAmount = ld.getPaymentAmount();
		if (ld.getLoanStatus() == StatusType.FORECLOSURE_ACCEPTED) {
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

			// sending mail confirming of getting final payment via foreclosure
			String mailContent = "We've recieved an amount of Rs " + oldPaymentAmount + " via " + pt.getPaymentMethod()
					+ " payment. This was your final payment, Thanks for paying us back. Your loan payback is now completed";

			mailSender.setEmailSubject("Congratulations, you've paid back your complete loan.");
			mailSender.setReceiverEmail(ld.getCustomer().getEmail());
			mailSender.setEmailContent(mailContent);
			mailSender.sendEmail();

			return ph;

		} else {
			throw new GlobalLoanException("404", "Not accepted for foreclosure");
		}
	}

	// get loan details by it's id
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

	// use updateLoanStatusFromLoanId for this
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
