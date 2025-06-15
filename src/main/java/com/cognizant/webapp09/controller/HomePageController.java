package com.cognizant.webapp09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
	@GetMapping({"/", "/home"})
	public String getHomePage() {
		System.out.println("i am in home page congtroller");
		return "index.jsp";
	}
}