package com.cognizant.webapp09.entity;

import java.sql.Timestamp; // Using java.sql.Timestamp for consistency with SQL DATETIME/TIMESTAMP types

public class CartItem {
    private int cartItemId;
    private int cartId;
    private int foodItemId;
    private int quantity; // NEWLY ADDED FIELD
    private Timestamp addedAt;
    private double priceAtTimeOfAddition;

    // Optional: To represent the many-to-one relationship with Cart
    private Cart cart;
    // Optional: To represent the many-to-one relationship with FoodItem (assuming FoodItem entity exists)
    private FoodItem foodItem;

    public CartItem() {}

    // Constructor with quantity
    public CartItem(int cartId, int foodItemId, int quantity, double priceAtTimeOfAddition) {
        this.cartId = cartId;
        this.foodItemId = foodItemId;
        this.quantity = quantity;
        this.priceAtTimeOfAddition = priceAtTimeOfAddition;
    }

    // Full constructor with cartItemId and quantity
    public CartItem(int cartItemId, int cartId, int foodItemId, int quantity, Timestamp addedAt, double priceAtTimeOfAddition) {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.foodItemId = foodItemId;
        this.quantity = quantity;
        this.addedAt = addedAt;
        this.priceAtTimeOfAddition = priceAtTimeOfAddition;
    }

    // Getters
    public int getCartItemId() {
        return cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public int getFoodItemId() {
        return foodItemId;
    }

    public int getQuantity() { // NEW GETTER
        return quantity;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public double getPriceAtTimeOfAddition() {
        return priceAtTimeOfAddition;
    }

    public Cart getCart() {
        return cart;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    // Setters
    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setFoodItemId(int foodItemId) {
        this.foodItemId = foodItemId;
    }

    public void setQuantity(int quantity) { // NEW SETTER
        this.quantity = quantity;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    public void setPriceAtTimeOfAddition(double priceAtTimeOfAddition) {
        this.priceAtTimeOfAddition = priceAtTimeOfAddition;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    @Override
    public String toString() {
        return "CartItem [cartItemId=" + cartItemId + ", cartId=" + cartId + ", foodItemId=" + foodItemId +
               ", quantity=" + quantity + // Added quantity to toString
               ", addedAt=" + addedAt + ", priceAtTimeOfAddition=" + priceAtTimeOfAddition + "]";
    }
}