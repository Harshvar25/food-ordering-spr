package com.cognizant.webapp09.customer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cognizant.webapp09.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

    @Override
    public boolean insertCustomer(Customer customer) {
        boolean status = false;

        try {
            int noOfRows = jdbcTemplate.update(
                "INSERT INTO customer_info(customer_id, name, email, phone, password, address) VALUES(?,?,?,?,?,?)",
                customer.getCustomerId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getPassword(),
                customer.getAddress()
            );

            if (noOfRows != 0) {
                status = true;
            }
        } catch (DuplicateKeyException e) {
            System.err.println("Error: Duplicate customer entry. Details: " + e.getMessage());
            System.out.println("Registration failed: A customer with this ID, email, or phone number already exists.");
            status = false;
        } catch (DataAccessException e) {
            e.printStackTrace(); 

            String errorMessage = e.getMessage();

            if (errorMessage != null && (errorMessage.contains("chk_phone_length") || 
                                         errorMessage.contains("Data too long") || 
                                         errorMessage.contains("incorrect string value") || 
                                         errorMessage.contains("phone number cannot exceed 10 digits") || 
                                         errorMessage.contains("check constraint 'customer_info.chk_phone_length' is violated") 
                                        )) {
                System.err.println("Error: The phone number provided is too long. Please enter a number with a maximum of 10 digits.");
            } else {
                System.err.println("Error inserting customer due to a database issue: " + errorMessage);
            }
            status = false;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); 
            status = false;
        }

        return status;
    }

	
	@Override
	public boolean checkCredintials(Customer customer) {
		boolean status = false;
		
		try {
			Customer resultCustomer = jdbcTemplate.queryForObject(
					"SELECT customer_id, name, email, phone, password, address, created_at FROM customer_info WHERE email=? AND password=?",
					getCustomerRowMapper(),customer.getEmail(),customer.getPassword());
			
			customer.setCustomerId(resultCustomer.getCustomerId());
			customer.setName(resultCustomer.getName());
			customer.setAddress(resultCustomer.getAddress());
			customer.setPhone(resultCustomer.getPhone());
			customer.setCreatedAt(resultCustomer.getCreatedAt());
			
			status = true;
		} catch(EmptyResultDataAccessException erdaEx) {
			erdaEx.printStackTrace();
		}
		
		return status;
	}
	
	@Override
	public boolean deleteCustomer(int customerId) {
		boolean status = false;
		int noOfRows = jdbcTemplate.update("DELETE FROM customer_info WHERE customer_id=?",customerId);
		if(noOfRows != 0) {
			status = true;
		}
		return status;
	}
	
	@Override
	public List<Customer> selectAllCustomer(){
		List<Customer> customerList = jdbcTemplate.query("SELECT customer_id, name, email, phone, password, address, created_at FROM customer_info",getCustomerRowMapper());
		return customerList;
	}
	
	private RowMapper<Customer> getCustomerRowMapper(){
		RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException{
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setEmail(rs.getString(3));
				customer.setPhone(rs.getString(4));
				customer.setPassword(rs.getString(5));
				customer.setAddress(rs.getString(6));
				customer.setCreatedAt(rs.getString(7));
				
				return customer;
			}
		};
		return customerRowMapper;
	}
}
