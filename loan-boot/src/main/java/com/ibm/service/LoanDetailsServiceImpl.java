package com.ibm.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Customer;
import com.ibm.entity.LoanDetails;
import com.ibm.enums.StatusType;
import com.ibm.repo.CustomerRepository;
import com.ibm.repo.LoanDetailsRepository;

@Service
public class LoanDetailsServiceImpl implements LoanDetailsService {

	@Autowired
	private LoanDetailsRepository loanDetailsRepo;
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public LoanDetails createLoanDetails(LoanDetails ld, int custId) {
		Customer cust = customerRepo.findById(custId).get();
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
			
			//saving
			loanDetailsRepo.save(ld); // save the new obj (loanDetails) before the customer
			customerRepo.save(cust);
			return ld;
		}
		return null; /// handle error
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
		ld.setLoanStatus(status);
		loanDetailsRepo.save(ld);
		return ld;
	}

	@Override
	public LoanDetails updateLoanStatusFromCustId(int custId, StatusType status) {
		Customer cust = customerRepo.findById(custId).get();
		LoanDetails ld = cust.getLoanDetail();
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

	@Override
	public double getOutstandingPrincipal(int loanId) {
		LoanDetails ld = loanDetailsRepo.findById(loanId).get();
		return ld.getOutstandingPrincipal();
	}

	@Override
	public void updateOutstandingPrincipal(int loanId, double op) {
		LoanDetails ld = loanDetailsRepo.findById(loanId).get();
		ld.setOutstandingPrincipal(op);
		loanDetailsRepo.save(ld);
	}

	@Override
	public LoanDetails updaLoanDetails(LoanDetails ld) {
		return loanDetailsRepo.save(ld);
	}

}
