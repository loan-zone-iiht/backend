package com.ibm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class signup_controller {
		@RequestMapping("customer_signup_controller")
		public String signc() {
			System.out.println("trying cus-signup controller");
			return "customer_signup";
		}
		@RequestMapping("manager_signup_controller")
		public String signm() {
			System.out.println("trying manager-signup controller");
			return "manager_signup";
		}
}
