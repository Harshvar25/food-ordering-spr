package com.cognizant.webapp09.entity;

public class Customer {

	private int customer_id;
	private String name;
	private String email;
	private String phone;
	private String password;
	private String address;
	private String created_at;

	public Customer() {
	}

	public Customer(int customer_id, String name, String email, String phone,
			String password, String address, String created_at) {
		this.customer_id = customer_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = address;
		this.created_at = created_at;
	}

	public int getCustomerId() {
		return customer_id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public String getCreatedAt() {
		return created_at;
	}

	public void setCustomerId(int customer_id) {
		this.customer_id = customer_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Customer [customerId =" + customer_id + ", customerName=" + name+ ", customerEmail= "+ email +", customerPhone= "+ phone +", customerPassword=" + password +", customerAddress "+ address +", customerCreationDate= "+ created_at+ "]";
	}

}
