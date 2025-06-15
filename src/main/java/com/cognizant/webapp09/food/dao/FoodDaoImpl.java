package com.cognizant.webapp09.food.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.webapp09.entity.FoodItem;

@Repository
public class FoodDaoImpl implements FoodDao{
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public FoodDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
//    insert food item in food_item table
    @Override
    public boolean insertFoodItem(FoodItem foodItem) {
        boolean status = false;
        
        int noOfRows = jdbcTemplate.update("INSERT INTO food_item(name,description,price,category) VALUES(?,?,?,?)",
                foodItem.getName(),
                foodItem.getDescription(),
                foodItem.getPrice(),
                foodItem.getCategory());
        if(noOfRows != 0) {
            status = true;
        }
        
        return status;
    }
    
//    delete food item from the food_item table using food_id

    @Override
    @Transactional 
    public boolean deleteFood(int id) {
    	
        int noOfDeleteCartRows = jdbcTemplate.update("DELETE FROM cart_item WHERE food_item_id = ?", id);
        
        int noOfFoodRows = jdbcTemplate.update("DELETE FROM food_item WHERE id = ?", id);
        
        return noOfFoodRows > 0; 
    }
    
//    view full list of  food items 
    @Override
    public List<FoodItem> getAllFoodItems(){
        List<FoodItem> foodList = jdbcTemplate.query(
                "SELECT id, name,description, price , category FROM food_item",
                getFoodListRowMapper());

        return foodList;
    }

    @Override
    public FoodItem getFoodItemById(int id) {
        String sql = "SELECT id, name, description, price, category FROM food_item WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, getFoodListRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    private RowMapper<FoodItem> getFoodListRowMapper() {
        return (rs, rowNum) -> {
            FoodItem foodItem = new FoodItem();
            foodItem.setId(rs.getInt("id")); 
            foodItem.setName(rs.getString("name"));
            foodItem.setDescription(rs.getString("description"));
            foodItem.setPrice(rs.getDouble("price"));
            foodItem.setCategory(rs.getString("category"));
            return foodItem;
        };
    }
}