package com.cognizant.webapp09.customer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cognizant.webapp09.cart.service.CartService;
import com.cognizant.webapp09.entity.Customer;
import com.cognizant.webapp09.entity.FoodItem;
import com.cognizant.webapp09.food.dao.FoodDao;

@Controller
@RequestMapping("/addtocart")
public class AddToCartController {
	private CartService cartService;
	private FoodDao foodDaoImpl;

	@Autowired
	public AddToCartController(CartService cartService, FoodDao foodDaoImpl) {
		this.cartService = cartService;
		this.foodDaoImpl = foodDaoImpl;
	}

	@PostMapping
	public String performAddToCart(HttpSession session, @RequestParam("id") int foodItemId,
			@RequestParam("quantity") int quantity, RedirectAttributes redirectAttributes) {

		Customer customer = (Customer) session.getAttribute("CUSTOMER");

		if (customer == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Please log in to add items to your cart.");
			return "customerSignInForm.jsp";
		}

		if (quantity <= 0) {
			redirectAttributes.addFlashAttribute("errorMessage", "Quantity must be greater than zero to add to cart.");
			return "redirect:/customerFoodList.jsp";
		}

		FoodItem foodItem = foodDaoImpl.getFoodItemById(foodItemId);
		if (foodItem == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Selected food item not found.");
			return "redirect:/customerFoodList.jsp";
		}

		boolean success = cartService.addOrUpdateCartItem(customer.getCustomerId(), foodItemId, quantity,
				foodItem.getPrice());

		if (success) {
			redirectAttributes.addFlashAttribute("successMessage", "Item added/updated in cart successfully!");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage",
					"Failed to add/update item in cart. Please try again.");
		}

		return "redirect:/customerFoodList.jsp";
	}
}