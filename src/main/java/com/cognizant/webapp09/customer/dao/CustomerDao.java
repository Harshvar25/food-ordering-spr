package com.cognizant.webapp09.customer.dao;

import java.util.List;

import com.cognizant.webapp09.entity.Customer;

public interface CustomerDao {
	public boolean insertCustomer(Customer customer);
	public boolean checkCredintials(Customer customer);
	public boolean deleteCustomer(int customerId);
	public List<Customer> selectAllCustomer();
}
