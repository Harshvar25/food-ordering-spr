package com.cognizant.webapp09.cart.dao;

import com.cognizant.webapp09.entity.CartItem; // Import CartItem as the primary entity for cart contents
import java.util.List;

public interface CartDao {

    Integer getCartIdByCustomerId(int customerId, boolean createIfNotFound);

    Integer createCartForCustomer(int customerId);

    CartItem getCartItemByCartAndFoodItem(int cartId, int foodItemId);

    boolean insertCartItem(CartItem cartItem);

    public boolean updateCartItemQuantity(int cartItemId, int newQuantity);

    boolean removeCartItem(int cartItemId);

    List<CartItem> getCartItemsByCustomerId(int customerId);

    boolean clearCartByCustomerId(int customerId);
}