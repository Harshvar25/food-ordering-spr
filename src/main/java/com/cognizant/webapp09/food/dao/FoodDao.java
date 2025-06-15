package com.cognizant.webapp09.food.dao;

import java.util.List;

import com.cognizant.webapp09.entity.FoodItem;

public interface FoodDao {
    boolean insertFoodItem(FoodItem foodItem);
    boolean deleteFood(int id);
    List<FoodItem> getAllFoodItems();    
    FoodItem getFoodItemById(int id);
}