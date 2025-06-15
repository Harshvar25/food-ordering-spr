package com.cognizant.webapp09.customer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cognizant.webapp09.cart.service.CartService;
import com.cognizant.webapp09.entity.CartItem; 
import com.cognizant.webapp09.entity.Customer;

@Controller
@RequestMapping("/viewcart")
public class ViewCartItems {
    private CartService cartService;

    @Autowired
    public ViewCartItems(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ModelAndView showCartItems(HttpSession session, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        Customer customer = (Customer) session.getAttribute("CUSTOMER");

        if (customer == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to view your cart.");
            return new ModelAndView("redirect:/customersignin");
        }

        int customerId = customer.getCustomerId();

        List<CartItem> cartItems = cartService.getCartItemsByCustomerId(customerId); 

        request.setAttribute("cartList", cartItems); 

        double totalAmount = 0.0;
        for (CartItem item : cartItems) { 
            if (item.getFoodItem() != null) {
                 totalAmount += (item.getQuantity() * item.getPriceAtTimeOfAddition());
            } else {
                 totalAmount += (item.getQuantity() * item.getPriceAtTimeOfAddition());
            }
        }

        ModelAndView modelAndView = new ModelAndView("customerCartList.jsp");
        modelAndView.addObject("cartItems", cartItems); 
        modelAndView.addObject("totalAmount", totalAmount);

        return modelAndView;
    }
}