package com.ibm.service;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.email.MailSender;
import com.ibm.entity.Manager;
import com.ibm.enums.RoleOptions;
import com.ibm.exception.GlobalLoanException;
import com.ibm.message.MessageSender;
import com.ibm.repo.ManagerRepository;

/**
 * Class {ManagerServiceImpl} is a service class extending {ManagerService}
 * for manager entity, which uses the methods from
 * manager repository.
 * 
 * @author Saswata Dutta
 * @author Sayak Mukherjee
 */

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
		// setting the role

		mgr.setRole(RoleOptions.MANAGER);
		return managerRepo.save(mgr);
	}

	// auth for manager
	/**
	 * @author Saswata Dutta
	 * */
	@Override
	public Manager loginManager(String email, String phone, String password, int otp) throws GlobalLoanException {
		Manager mgr = managerRepo.loginManager(email, phone);
		System.err.println(mgr);
		if (mgr == null) {
			throw new GlobalLoanException("404", "No manager with this credential");
		} else if (password!=null &&  mgr.getPassword().equals(password)) {
			// to make sure if password is wrong but provided, the below condition should
			// not enter. And also mgr.getOtp() is not null and otp(primitive int) is not 0(default)
			return mgr;
		} // to make sure if password is wrong but provided, the below condition should
		// not enter. And also mgr.getOtp() is not null and otp(primitive int) is not 0(default)
	 else if(mgr.getOtp()!=null && otp!=0 && this.verifyOtp(mgr, otp)){
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
