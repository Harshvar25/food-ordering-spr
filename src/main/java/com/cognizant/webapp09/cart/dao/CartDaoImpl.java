package com.cognizant.webapp09.cart.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.webapp09.entity.CartItem;
import com.cognizant.webapp09.entity.FoodItem;

@Repository
public class CartDaoImpl implements CartDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CartDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Integer getCartIdByCustomerId(int customerId, boolean createIfNotFound) {
		try {
			String sql = "SELECT cart_id FROM cart WHERE customer_id = ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, customerId);
		} catch (EmptyResultDataAccessException e) {
			if (createIfNotFound) {
				return createCartForCustomer(customerId);
			}
			return null;
		}
	}

	@Transactional
	public Integer createCartForCustomer(int customerId) {
		String sql = "INSERT INTO cart(customer_id) VALUES(?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int noOfRows = jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, customerId);
			return ps;
		}, keyHolder);

		if (noOfRows > 0 && keyHolder.getKey() != null) {
			return keyHolder.getKey().intValue();
		}
		return null;
	}
	
	public CartItem getCartItemByCartAndFoodItem(int cartId, int foodItemId) {
		try {
			String sql = "SELECT cart_item_id, cart_id, food_item_id, quantity, added_at, price_at_time_of_addition "
					+ "FROM cart_item WHERE cart_id = ? AND food_item_id = ?";
			return jdbcTemplate.queryForObject(sql, getCartItemRowMapper(), cartId, foodItemId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public boolean insertCartItem(CartItem cartItem) {
		String sql = "INSERT INTO cart_item(cart_id, food_item_id, quantity, price_at_time_of_addition, added_at) VALUES(?, ?, ?, ?, ?)";

		Timestamp addedAt = cartItem.getAddedAt();
		if (addedAt == null) {
			addedAt = Timestamp.valueOf(LocalDateTime.now());
			cartItem.setAddedAt(addedAt);
		}

		int noOfRows = jdbcTemplate.update(sql, cartItem.getCartId(), cartItem.getFoodItemId(),
				cartItem.getQuantity(), cartItem.getPriceAtTimeOfAddition(), addedAt);
		return noOfRows > 0;
	}

	@Override
	public boolean updateCartItemQuantity(int cartItemId, int newQuantity) {
		String sql = "UPDATE cart_item SET quantity = ?, added_at = ? WHERE cart_item_id = ?";

		Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());

		int noOfRows = jdbcTemplate.update(sql, newQuantity, currentTimestamp, cartItemId);
		return noOfRows > 0;
	}

	@Override
	public boolean removeCartItem(int cartItemId) {
		String sql = "DELETE FROM cart_item WHERE cart_item_id = ?";
		int noOfRows = jdbcTemplate.update(sql, cartItemId);
		return noOfRows > 0;
	}

	@Override
	public List<CartItem> getCartItemsByCustomerId(int customerId) {
		Integer cartId = getCartIdByCustomerId(customerId, false);
		if (cartId == null) {
			return List.of();
		}

		String sql = "SELECT ci.cart_item_id, ci.cart_id, ci.food_item_id, ci.quantity, ci.added_at, ci.price_at_time_of_addition, "
				+ "f.id AS food_id, f.name AS food_name, f.description AS food_description, f.price AS current_food_price, f.category AS food_category "
				+ "FROM cart_item ci " + "JOIN food_item f ON ci.food_item_id = f.id " + "WHERE ci.cart_id = ?";

		return jdbcTemplate.query(sql, getCartItemWithFoodDetailsRowMapper(), cartId);
	}

	@Override
	public boolean clearCartByCustomerId(int customerId) {
		Integer cartId = getCartIdByCustomerId(customerId, false);
		if (cartId == null) {
			return false;
		}

		String sql = "DELETE FROM cart_item WHERE cart_id = ?";
		int noOfRows = jdbcTemplate.update(sql, cartId);
		return noOfRows > 0;
	}

	private RowMapper<CartItem> getCartItemRowMapper() {
		return (rs, rowNum) -> {
			CartItem cartItem = new CartItem();
			cartItem.setCartItemId(rs.getInt("cart_item_id"));
			cartItem.setCartId(rs.getInt("cart_id"));
			cartItem.setFoodItemId(rs.getInt("food_item_id"));
			cartItem.setQuantity(rs.getInt("quantity"));
			cartItem.setAddedAt(rs.getTimestamp("added_at"));
			cartItem.setPriceAtTimeOfAddition(rs.getDouble("price_at_time_of_addition"));
			return cartItem;
		};
	}

	private RowMapper<CartItem> getCartItemWithFoodDetailsRowMapper() {
		return (rs, rowNum) -> {
			CartItem cartItem = new CartItem();
			cartItem.setCartItemId(rs.getInt("cart_item_id"));
			cartItem.setCartId(rs.getInt("cart_id"));
			cartItem.setFoodItemId(rs.getInt("food_item_id"));
			cartItem.setQuantity(rs.getInt("quantity"));
			cartItem.setAddedAt(rs.getTimestamp("added_at"));
			cartItem.setPriceAtTimeOfAddition(rs.getDouble("price_at_time_of_addition"));

			FoodItem foodItem = new FoodItem();
			foodItem.setId(rs.getInt("food_id"));
			foodItem.setName(rs.getString("food_name"));
			foodItem.setDescription(rs.getString("food_description"));
			foodItem.setPrice(rs.getDouble("current_food_price"));
			foodItem.setCategory(rs.getString("food_category"));

			cartItem.setFoodItem(foodItem);

			return cartItem;
		};
	}
}