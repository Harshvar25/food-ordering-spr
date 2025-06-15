package com.cognizant.webapp09.cart.service;

import java.util.List;
import com.cognizant.webapp09.entity.CartItem;

public interface CartService {

    Integer getCartIdByCustomerId(int customerId, boolean createIfNotFound);

    CartItem getCartItemByCartAndFoodItem(int cartId, int foodItemId);

    boolean addOrUpdateCartItem(int customerId, int foodItemId, int quantity, double priceAtTimeOfAddition);

    boolean updateCartItemQuantity(int cartItemId, int newQuantity);

    boolean removeCartItem(int cartItemId);

    List<CartItem> getCartItemsByCustomerId(int customerId);

    boolean clearCartByCustomerId(int customerId);
}