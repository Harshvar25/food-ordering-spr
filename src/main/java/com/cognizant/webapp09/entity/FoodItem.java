package com.cognizant.webapp09.entity;

public class FoodItem {
	private int id;
	private String name;
	private String description;
	private double price;
	private String category;
	
	public FoodItem() {}
	
	public FoodItem(int id,String name,String description,double price,String category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Food Item [id="+id+", name="+name+", description="+description+", price="+price+", category="+category+"]";
	}
}
