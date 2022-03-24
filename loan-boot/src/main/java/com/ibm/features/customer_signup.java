package com.ibm.features;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.entity.Customer;
import com.ibm.entity.Pan;
import com.ibm.service.CustomerService;
import com.ibm.service.PanService;



@Controller
public class customer_signup {
	@Autowired
	private PanService panService;
	@Autowired
	private CustomerService customerService;
	@RequestMapping("customer_signup")
	public ModelAndView cus_sign(@RequestParam("name") String name,@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("panCard") String panCard, @RequestParam("cibilScore") int cibilScore,@RequestParam("salary") double salary,
			@RequestParam("phone") String phone) {

	//Double salary=Double.parseDouble(salary);

	//setting pan
	Pan pan = new Pan(panCard, cibilScore);
	panService.createPan(pan);
	//setting new customer
	Customer new_customer=new Customer();
	new_customer.setName(name);
	new_customer.setEmail(email);

	new_customer.setPassword(password);
	new_customer.setPan(pan);
	new_customer.setSalary(salary);
	new_customer.setPhone(phone);
	customerService.createCustomer(new_customer, panCard);
	ModelAndView mv=new ModelAndView();
	mv.addObject("new_user_type","customer");
	mv.addObject("name",name);
	mv.setViewName("sucessful_signup");
	return mv;
	}
	
}
