package com.ibm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Manager;
import com.ibm.pojo.LoginPOJO;
import com.ibm.service.ManagerService;

@RestController
public class ManagerController {
	@Autowired
	private ManagerService mgrService;

	@PostMapping(path = "/manager-signup", consumes = "application/json")
	public Manager managerSignup(@RequestBody Manager mgr) {
		return mgrService.createManager(mgr);
	}

	@PostMapping(path = "/manager-login", consumes = "application/json")
	public Manager managerLogin(@RequestBody LoginPOJO login) {

		return mgrService.loginManager(login.getEmail(), login.getPhone(), login.getPassword(), login.getOtp());
	}

	@PostMapping(path = "/manager-send-otp", consumes = "application/json")
	public Manager managerCheckOtp(@RequestBody LoginPOJO login) {

		return mgrService.sendOtp(login.getEmail(), login.getPhone());
	}
	
	// made for solely otp verify
//	@PostMapping(path = "/manager-verify-otp", consumes = "application/json")
//	public Manager managerVerifyOtp(@RequestBody LoginPOJO login) {
//
//		return mgrService.verifyOtp(login.getId(),login.getOtp());
//	}

}
