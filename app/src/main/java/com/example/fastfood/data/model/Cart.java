package com.example.fastfood.data.model;

import java.util.List;

public class Cart {
    private Long id;
    private Long userId;
    private List<CartItem> cartItems;
    private Double totalPrice;

    public Cart() {
    }

    public Cart(Long id, Long userId, List<CartItem> cartItems, Double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
