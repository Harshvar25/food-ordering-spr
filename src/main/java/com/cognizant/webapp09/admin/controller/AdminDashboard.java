package com.cognizant.webapp09.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboard {
	
	@GetMapping("/admindashboard")
	public String getDashboard() {
		return "adminDashboard.jsp";
	}
}
