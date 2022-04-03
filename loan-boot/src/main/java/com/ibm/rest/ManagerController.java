package com.ibm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Manager;
import com.ibm.pojo.LoginPOJO;
import com.ibm.service.ManagerService;
/**
 * Class {ManagerController} is the controller class. Mainly having the routes
 * related to manager entity. Mainly uses ManagerService methods.
 * 
 * Controller paths starting with /manager/ or /manager- needs a header role and
 * it should be MANAGER which is a enum of type RoleOptions. 
 * [Excluding /manager-signup, /manager-login, /manager-send-otp]
 * 
 * @author Saswata Dutta
 */
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

}
