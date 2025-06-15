package com.cognizant.webapp09.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cognizant.webapp09.customer.service.CustomerService;
import com.cognizant.webapp09.entity.Customer;

@Controller
@RequestMapping("/customersignin")
public class CustomerSignInController {
	private CustomerService customerService;
	
	@Autowired
	public CustomerSignInController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public String getSignInForm() {
		return "customerSignInForm.jsp";
	}
	
	@PostMapping
	public String performSignInOperation(@ModelAttribute Customer customer,HttpServletRequest request, HttpSession session) {
		boolean signInStatus = customerService.signIn(customer);
		
		if(signInStatus) {
			session.setAttribute("CUSTOMER", customer);
			return "customerDashboard.jsp";
		}else {
			request.setAttribute("SIGNINRESULT", "No");
			return "customerSignInForm.jsp";
		}
	}
}
