package com.cognizant.webapp09.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerDashboard {
	
	@GetMapping("/customerdasboard")
	public String getDashboard() {
		return "customerDashboard.jsp";
	}
}
