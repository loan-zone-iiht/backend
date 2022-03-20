package com.ibm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.entity.Customer;
import com.ibm.entity.Pan;
import com.ibm.service.CustomerService;
import com.ibm.service.PanService;

@SpringBootTest
class LoanBootApplicationTests {
	
	@Autowired
	private PanService panService;
	@Autowired
	private CustomerService customerService;
	
	@Test
	void contextLoads() {
		// creating some pans
		Pan pan1 = new Pan("XXXA", 750);
		panService.createPan(pan1);
		
		Pan pan2 = new Pan("XXXB", 710);
		panService.createPan(pan2);
		
		// creating some customers
		Customer cust1 = new Customer();
		cust1.setName("sergio ramos");
		cust1.setEmail("sergio@ramos.com");
		cust1.setPhone("9876543210");
		customerService.createCustomer(cust1, "XXXA");
		
		Customer cust2 = new Customer();
		cust1.setName("thiago silva");
		cust1.setEmail("thiago@silva.com");
		cust1.setPhone("9876543210");
		customerService.createCustomer(cust2, "XXXB");
	}

}
