package com.example.fastfood.data.model;

public class CartItem {
    private Long id;
    private Cart cart;
    private Food food;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Long id, Cart cart, Food food, int quantity) {
        this.id = id;
        this.cart = cart;
        this.food = food;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
