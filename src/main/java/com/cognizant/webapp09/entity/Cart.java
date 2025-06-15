package com.cognizant.webapp09.entity;

import java.sql.Timestamp; 
import java.util.List; 

public class Cart {
    private int cartId;
    private int customerId;
    private Timestamp createdAt; 


    private List<CartItem> cartItems;

    public Cart() {}

    public Cart(int cartId, int customerId, Timestamp createdAt) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.createdAt = createdAt;
    }

    public Cart(int customerId) {
        this.customerId = customerId;
    }

    // Getters
    public int getCartId() {
        return cartId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // Setters
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Cart [cartId=" + cartId + ", customerId=" + customerId + ", createdAt=" + createdAt + "]";
    }
}