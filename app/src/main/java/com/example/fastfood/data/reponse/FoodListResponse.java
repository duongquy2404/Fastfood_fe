package com.example.fastfood.data.reponse;

import com.example.fastfood.data.model.Food;

import java.util.List;

public class FoodListResponse {
    private List<Food> foodList;
    private String message;

    public FoodListResponse() {
    }

    public FoodListResponse(List<Food> foodList, String message) {
        this.foodList = foodList;
        this.message = message;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
