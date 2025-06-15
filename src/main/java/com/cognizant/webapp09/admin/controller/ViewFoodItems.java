package com.cognizant.webapp09.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cognizant.webapp09.entity.FoodItem;
import com.cognizant.webapp09.food.service.FoodItemService;

@Controller
public class ViewFoodItems {
	private FoodItemService foodItemService;
	
	@Autowired
	public ViewFoodItems(FoodItemService foodItemService) {
		this.foodItemService = foodItemService;
	}
	
	@GetMapping("/fooditemlist")
	public String performFoodListOperation(HttpSession session) {
		List<FoodItem> foodList = foodItemService.getAllFoodItems();
		
		session.setAttribute("FOODLIST", foodList);
		
		return "foodList.jsp";
	}
}
