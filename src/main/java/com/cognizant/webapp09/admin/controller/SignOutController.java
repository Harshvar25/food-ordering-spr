package com.cognizant.webapp09.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signout")
public class SignOutController {
	@GetMapping
	public String getSignOut(HttpSession session) {
		if(session != null)
			session.invalidate();
		
		return "/adminSignInForm.jsp";
	}
}
