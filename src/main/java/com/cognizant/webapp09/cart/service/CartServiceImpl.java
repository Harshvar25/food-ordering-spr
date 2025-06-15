package com.cognizant.webapp09.cart.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.webapp09.cart.dao.CartDao;
import com.cognizant.webapp09.entity.CartItem;

@Service
public class CartServiceImpl implements CartService {

    private CartDao cartDao;

    @Autowired
    public CartServiceImpl(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public Integer getCartIdByCustomerId(int customerId, boolean createIfNotFound) {
        return cartDao.getCartIdByCustomerId(customerId, createIfNotFound);
    }

    @Override
    public CartItem getCartItemByCartAndFoodItem(int cartId, int foodItemId) {
        return cartDao.getCartItemByCartAndFoodItem(cartId, foodItemId);
    }

    @Override
    @Transactional
    public boolean addOrUpdateCartItem(int customerId, int foodItemId, int quantity, double priceAtTimeOfAddition) {
        Integer cartId = cartDao.getCartIdByCustomerId(customerId, true);
        if (cartId == null) {
            return false;
        }

        CartItem existingCartItem = cartDao.getCartItemByCartAndFoodItem(cartId, foodItemId);

        if (existingCartItem != null) {
            int newQuantity = existingCartItem.getQuantity() + quantity;
            return cartDao.updateCartItemQuantity(existingCartItem.getCartItemId(), newQuantity);
        } else {
            CartItem newCartItem = new CartItem(cartId, foodItemId, quantity, priceAtTimeOfAddition);
            newCartItem.setAddedAt(Timestamp.valueOf(LocalDateTime.now()));
            return cartDao.insertCartItem(newCartItem);
        }
    }

    @Override
    public boolean updateCartItemQuantity(int cartItemId, int newQuantity) {
        if (newQuantity <= 0) {
            return cartDao.removeCartItem(cartItemId);
        }
        return cartDao.updateCartItemQuantity(cartItemId, newQuantity);
    }

    @Override
    public boolean removeCartItem(int cartItemId) {
        return cartDao.removeCartItem(cartItemId);
    }

    @Override
    public List<CartItem> getCartItemsByCustomerId(int customerId) {
        return cartDao.getCartItemsByCustomerId(customerId);
    }

    @Override
    public boolean clearCartByCustomerId(int customerId) {
        return cartDao.clearCartByCustomerId(customerId);
    }
}