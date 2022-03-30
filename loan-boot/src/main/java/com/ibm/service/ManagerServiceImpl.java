package com.ibm.service;

import java.text.ParseException;
import java.util.Random;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.email.MailSender;
import com.ibm.entity.Manager;
import com.ibm.exception.GlobalLoanException;
import com.ibm.message.MessageSender;
import com.ibm.repo.ManagerRepository;

import com.ibm.pojo.SMS;


@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerRepository managerRepo;
	@Autowired
	private MailSender mailSender;
	
	@Autowired
     private MessageSender msender;

	Supplier<Integer> generateOtp = () -> {
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);

		return n;
	};

	@Override
	public Manager createManager(Manager mgr) throws GlobalLoanException {
		return managerRepo.save(mgr);
	}

	// auth for manager
	@Override
	public Manager loginManager(String email, String phone, String password, int otp) throws GlobalLoanException {
		Manager mgr = managerRepo.loginManager(email, phone);
		System.err.println(mgr);
		if (mgr == null) {
			throw new GlobalLoanException("404", "No manager with this credential");
		} else if (mgr.getPassword().equals(password)) {
			return mgr;
		} else if(this.verifyOtp(mgr, otp)){
			// verify otp
			return mgr;
		}else {	
			// wrong credentials
			throw new GlobalLoanException("403", "Wrong credentials");
		}
	}

	// get random manager to assign to a loan
	@Override
	public Manager getRandomManager() {
		return managerRepo.getRandomManager();
	}

	@Override
	public Manager sendOtp(String email, String phone) {

		Manager mgr = managerRepo.findByEmailOrPhone(email, phone);
		// generating otp
		int generatedOtp = generateOtp.get();
		System.err.println(generatedOtp);
		mgr.setOtp(generatedOtp);
		managerRepo.save(mgr);

		// sending mail
		mailSender.setEmailSubject("OTP from LOAN ZONE");
		mailSender.setReceiverEmail(mgr.getEmail());
		mailSender.setEmailContent("Manager OTP: " + generatedOtp);
		mailSender.sendEmail();
		
		
		msender.send(mgr.getPhone(),generatedOtp);
		
		
		
		return mgr;
	}

	@Override
	public boolean verifyOtp(Manager mgr, int otp) {
//		Manager mgr = managerRepo.findById(mId).get();
		if (mgr.getOtp() == otp) {
			return true;
		} else {
			return false;
		}
	}

}
