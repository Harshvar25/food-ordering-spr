package com.cognizant.webapp09.admin.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.webapp09.customer.service.CustomerService;
import com.cognizant.webapp09.entity.Customer;

@Controller
@RequestMapping("/deletecustomer")
public class DeleteCustomerController   {

    @Autowired
    private CustomerService customerServiceImpl; 

    
    @GetMapping("")
    public String performDeleteFoodOperation(@RequestParam("customerid") int customer_id, HttpSession session, HttpServletRequest request) {
        
        boolean deleteStatus = customerServiceImpl.removeCustomer(customer_id);
        

        if (deleteStatus) {
        	
            List<Customer> customerList = (List<Customer>) session.getAttribute("CUSTOMERLIST");

            if (customerList != null) {
                for (Iterator<Customer> iterator = customerList.iterator(); iterator.hasNext();) {
                    Customer customer = iterator.next();
                    if (customer.getCustomerId() == customer_id) {
                        iterator.remove(); 
                        break; 
                    }
                }
                session.setAttribute("CUSTOMERLIST", customerList);
            }
            request.setAttribute("DELETECUSTOMER", "Yes");
        } else {
            request.setAttribute("DELETECUSTOMER", "No");
        }

        return "customerList.jsp";
    }
}