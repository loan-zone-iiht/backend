package com.ibm.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.entity.Manager;
import com.ibm.service.ManagerService;

@Controller
public class manager_signup {
	@Autowired
	private ManagerService managerServe;
	@RequestMapping("manager_signup")
	public ModelAndView man_sign(@RequestParam("name") String name,@RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("phone") String phone) {
		Manager new_manager=new Manager();
		new_manager.setName(name);
		new_manager.setEmail(email);
		new_manager.setPassword(password);
		new_manager.setPhone(phone);
		managerServe.createManager(new_manager);
		ModelAndView mv=new ModelAndView();
		mv.addObject("new_user_type ","Manager");
		mv.addObject("name",name);
		mv.setViewName("sucessful_signup");
		return mv;
}
}
