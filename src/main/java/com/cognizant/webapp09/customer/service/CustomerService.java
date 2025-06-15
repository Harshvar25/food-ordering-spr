package com.cognizant.webapp09.customer.service;

import java.util.List;

import com.cognizant.webapp09.entity.Customer;

public interface CustomerService {
	public boolean signUp(Customer customer);
	public boolean signIn(Customer customer);
	public boolean removeCustomer(int customerId);
	public List<Customer> viewCustomerList();
}
