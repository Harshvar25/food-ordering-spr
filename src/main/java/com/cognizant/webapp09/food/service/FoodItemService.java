package com.cognizant.webapp09.food.service;

import java.util.List;

import com.cognizant.webapp09.entity.FoodItem;

public interface FoodItemService {
	public boolean insertItem(FoodItem foodItem);
	public boolean deleteItem(int id);
	public List<FoodItem> getAllFoodItems();
}
