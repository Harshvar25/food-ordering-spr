package com.cognizant.webapp09.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.webapp09.entity.FoodItem;
import com.cognizant.webapp09.food.dao.FoodDao;

@Service
public class FoodItemServiceImpl implements FoodItemService{
//	private FoodDao foodDao;
//	
//	@Autowired
//	public FoodItemServiceImpl(FoodDao foodDao) {
//		this.foodDao = foodDao;
//	}
	
	@Autowired
	private FoodDao foodDao;
	
	@Override
	public boolean insertItem(FoodItem foodItem) {
		return foodDao.insertFoodItem(foodItem);
	}
	
	@Override
	public boolean deleteItem(int id) {
		return foodDao.deleteFood(id);
	}
	
	@Override
	public List<FoodItem> getAllFoodItems(){
		return foodDao.getAllFoodItems();
	}
}
