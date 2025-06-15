package com.cognizant.webapp09.customer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customersignout")
public class CustomerSignOut {
	@GetMapping
	public String getSignOut(HttpSession session) {
		if(session != null)
			session.invalidate();
		
		return "/customerSignInForm.jsp";
	}
}
