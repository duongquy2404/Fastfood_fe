package com.example.fastfood.data.model;

public class CartItem {
    private Long id;
    private Long productId;
    private int quantity;
    private String note;

    public CartItem() {
    }

    public CartItem(Long id, Long productId, int quantity, String note) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
