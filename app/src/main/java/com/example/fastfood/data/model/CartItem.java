package com.example.fastfood.data.model;

public class CartItem {
    private Long id;
    private Long cartId;
    private Long foodId;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Long id, Long cartId, Long foodId, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
