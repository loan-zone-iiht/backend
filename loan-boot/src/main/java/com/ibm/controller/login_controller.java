package com.ibm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class login_controller {
	@RequestMapping("login")
	public String login() {
		System.out.println("trying login controller123");
		return "login";
	} 
}
