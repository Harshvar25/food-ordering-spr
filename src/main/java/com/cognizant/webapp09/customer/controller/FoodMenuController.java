package com.cognizant.webapp09.customer.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cognizant.webapp09.entity.FoodItem;
import com.cognizant.webapp09.food.service.FoodItemService;

@Controller
@RequestMapping("/viewmenu")
public class FoodMenuController {
	private FoodItemService foodItemService;
	
	@Autowired
	public FoodMenuController(FoodItemService foodItemService) {
		this.foodItemService = foodItemService;
	}
	
	@GetMapping
	public String performFoodListOperation(HttpSession session) {
		List<FoodItem> foodList = foodItemService.getAllFoodItems();
		session.setAttribute("FOODLIST", foodList);
		
		return "customerFoodList.jsp";
	}
}
