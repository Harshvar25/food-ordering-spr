package com.cognizant.webapp09.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.webapp09.cart.service.CartService;
import com.cognizant.webapp09.entity.Customer; 

@Controller
@RequestMapping("/deletefromcart")
public class RemoveCartItem {

	@Autowired
	private CartService cartServiceImpl;

	@GetMapping("")
	public String performRemoveCartItemOperation(@RequestParam("cartitemid") int cartItemId, HttpSession session,
			HttpServletRequest request) {

		Customer customer = (Customer) session.getAttribute("CUSTOMER");

		if (customer == null) {
			request.setAttribute("errorMessage", "Please log in to manage your cart.");
			return "customerSignInForm.jsp";
		}

		boolean deleteStatus = cartServiceImpl.removeCartItem(cartItemId);

		if (deleteStatus) {

			request.setAttribute("REMOVEDCARTITEM", "Yes");
		} else {
			request.setAttribute("REMOVEDCARTITEM", "No");
		}
		return "redirect:/viewcart"; 
	}
}