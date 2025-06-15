package com.cognizant.webapp09.admin.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.webapp09.entity.FoodItem;
import com.cognizant.webapp09.food.service.FoodItemService;

@Controller
@RequestMapping("/delete")
public class DeleteFoodItem   {

    @Autowired
    private FoodItemService foodItemServiceImpl; 

    
    @GetMapping("")
    public String performDeleteFoodOperation(@RequestParam("foodid") int food_Id, HttpSession session, HttpServletRequest request) {
        
        boolean deleteStatus = foodItemServiceImpl.deleteItem(food_Id);

        if (deleteStatus) {
            List<FoodItem> foodList = (List<FoodItem>) session.getAttribute("FOODLIST");

            if (foodList != null) {
                for (Iterator<FoodItem> iterator = foodList.iterator(); iterator.hasNext();) {
                    FoodItem food = iterator.next();
                    if (food.getId() == food_Id) {
                        iterator.remove(); 
                        break; 
                    }
                }
                session.setAttribute("FOODLIST", foodList);
            }
            request.setAttribute("DELETEFOODITEM", "Yes");
        } else {
            request.setAttribute("DELETEFOODITEM", "No");
        }

        return "foodList.jsp";
    }
}