package com.ibm.rest;

import com.ibm.pojo.SMS;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SMSController {
	
	@PostMapping(path = "/mobile", consumes = "application/json")
	public String sendOtp(@RequestBody SMS sms ) {
		
		try {
			System.out.println(sms.getPhoneNo());
		}catch(Exception e) {
			System.out.println("sdsd");
			e.printStackTrace();
		}
		
		return "Successfully OTP Sent";
		
	}

}
