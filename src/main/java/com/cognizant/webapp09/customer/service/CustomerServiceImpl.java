package com.cognizant.webapp09.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.webapp09.customer.dao.CustomerDao;
import com.cognizant.webapp09.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;
	
	@Autowired
	public CustomerServiceImpl(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Override
	public boolean signUp(Customer customer) {
		return customerDao.insertCustomer(customer);
	}
	
	@Override
	public boolean signIn(Customer customer) {
		return customerDao.checkCredintials(customer);
	}
	
	@Override
	public boolean removeCustomer(int customerId) {
		return customerDao.deleteCustomer(customerId);
	}
	
	@Override
	public List<Customer> viewCustomerList(){
		return customerDao.selectAllCustomer();
	}
}
