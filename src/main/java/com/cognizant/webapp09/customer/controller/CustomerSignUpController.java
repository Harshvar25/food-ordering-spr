package com.cognizant.webapp09.customer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.webapp09.customer.service.CustomerService;
import com.cognizant.webapp09.entity.Customer;


@Controller
@RequestMapping("/customersignup")
public class CustomerSignUpController {
	private CustomerService customerService;
	
	@Autowired
	public CustomerSignUpController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public String getCustomerSignUp() {
		return "customerSignUp.jsp";
	}
	
	@PostMapping
	public String postCustomerSignUp(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("password") String password,  @RequestParam("address") String address ,HttpServletRequest request) {
				
		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setPassword(password);
		customer.setAddress(address);

		boolean status = customerService.signUp(customer);

		if(status) {
			request.setAttribute("SIGNUPRESULT", "Yes");
			return "customerSignInForm.jsp";
		}else {
			request.setAttribute("SIGNUPRESULT", "No");
			return "customerSignUp.jsp";
		}
	}
}
