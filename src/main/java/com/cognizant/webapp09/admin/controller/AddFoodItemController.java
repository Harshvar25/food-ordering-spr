package com.cognizant.webapp09.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cognizant.webapp09.entity.FoodItem;
import com.cognizant.webapp09.food.service.FoodItemService;

@Controller
@RequestMapping("/addfooditem")
public class AddFoodItemController {
	private FoodItemService foodItemService;
	
	@Autowired
	public AddFoodItemController(FoodItemService foodItemService) {
		this.foodItemService = foodItemService;
	}
	
	@GetMapping
	public String getFoodItem() {
		return "addFoodItem.jsp";
	}
	
	@PostMapping
	public String postFoodItem(@ModelAttribute FoodItem foodItem, HttpServletRequest request) {
		System.out.println("inside postFoodItem");
		boolean isAdded = foodItemService.insertItem(foodItem);
		
		if(isAdded) {
			System.out.println("inside if(isAdded)");
			request.setAttribute("FOODITEMADDED", "Yes");
			return "addFoodItem.jsp";
		}else {
			request.setAttribute("FOODITEMADDED", "No");
			return "addFoodItem.jsp";
		}
	}
}
