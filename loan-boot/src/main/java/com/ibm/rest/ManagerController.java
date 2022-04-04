package com.ibm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Manager;
import com.ibm.pojo.LoginPOJO;
import com.ibm.pojo.ResponseHeader;
import com.ibm.service.ManagerService;
/**
 * Class {ManagerController} is the controller class. Mainly having the routes
 * related to manager entity. Mainly uses ManagerService methods.
 * 
 * Controller paths starting with /manager/ or /manager- needs a header role and
 * it should be MANAGER which is a enum of type RoleOptions. [Excluding
 * /manager-signup, /manager-login, /manager-send-otp]
 * 
 * @author Saswata Dutta
 */
@RestController
public class ManagerController {
	@Autowired
	private ManagerService mgrService;
	private ResponseHeader rh;

	@PostMapping(path = "/manager-signup", consumes = "application/json")
	public ResponseEntity<Manager> managerSignup(@RequestBody Manager mgr) {
//		return mgrService.createManager(mgr);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<Manager> res = new ResponseEntity<Manager>(mgrService.createManager(mgr), rh.getHeaders(),
				HttpStatus.OK);
		return res;
	}

	@PostMapping(path = "/manager-login", consumes = "application/json")
	public ResponseEntity<Manager> managerLogin(@RequestBody LoginPOJO login) {

//		return mgrService.loginManager(login.getEmail(), login.getPhone(), login.getPassword(), login.getOtp());
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<Manager> res = new ResponseEntity<Manager>(
				mgrService.loginManager(login.getEmail(), login.getPhone(), login.getPassword(), login.getOtp()),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}

	@PostMapping(path = "/manager-send-otp", consumes = "application/json")
	public ResponseEntity<Manager> managerCheckOtp(@RequestBody LoginPOJO login) {

//		return mgrService.sendOtp(login.getEmail(), login.getPhone());
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<Manager> res = new ResponseEntity<Manager>(
				mgrService.sendOtp(login.getEmail(), login.getPhone()),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}

}
