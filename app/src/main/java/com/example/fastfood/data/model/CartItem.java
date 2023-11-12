package com.example.fastfood.data.model;

public class CartItem {
    private Long id;
    private Long foodId;
    private int quantity;
    private String note;

    public CartItem() {
    }

    public CartItem(Long id, Long foodId, int quantity, String note) {
        this.id = id;
        this.foodId = foodId;
        this.quantity = quantity;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
