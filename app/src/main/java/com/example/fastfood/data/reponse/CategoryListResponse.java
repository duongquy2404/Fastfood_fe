package com.example.fastfood.data.reponse;

import com.example.fastfood.data.model.Category;

import java.util.List;

public class CategoryListResponse {
    private List<Category> categoryList;
    private String message;

    public CategoryListResponse() {
    }

    public CategoryListResponse(List<Category> categoryList, String message) {
        this.categoryList = categoryList;
        this.message = message;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
