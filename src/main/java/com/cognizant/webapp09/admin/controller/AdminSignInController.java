package com.cognizant.webapp09.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cognizant.webapp09.admin.service.AdminService;
import com.cognizant.webapp09.entity.Admin;


@Controller
@RequestMapping("/adminsignin")
public class AdminSignInController {
	private AdminService adminService;
	
	@Autowired
	public AdminSignInController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping
	public String getSignUpForm() {
		return "adminSignInForm.jsp";
	}
	
	@PostMapping
	public String performSignUpOperation(@ModelAttribute Admin admin) {
		boolean signUpStatus = adminService.signInAdmin(admin);
		
		if(signUpStatus) {
			return "adminDashboard.jsp";
		}else {
			return "adminSignInFailure.jsp";
		}
	}
}
