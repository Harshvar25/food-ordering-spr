package com.cognizant.webapp09.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cognizant.webapp09.customer.service.CustomerService;
import com.cognizant.webapp09.entity.Customer;

@Controller
public class ViewCustomerList {
	private CustomerService customerServiceImpl;
	
	@Autowired
	public ViewCustomerList(CustomerService customerServiceImpl) {
		this.customerServiceImpl = customerServiceImpl;
	}
	
	@GetMapping("/customerlist")
	public String performCustomerListOperation(HttpSession session) {
		List<Customer> customerList = customerServiceImpl.viewCustomerList();
		
		session.setAttribute("CUSTOMERLIST", customerList);
		
		return "customerList.jsp" ;
	}
}
