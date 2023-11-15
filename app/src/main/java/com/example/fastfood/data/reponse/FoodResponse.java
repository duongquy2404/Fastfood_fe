package com.example.fastfood.data.reponse;

import com.example.fastfood.data.model.Food;

public class FoodResponse {
    private Food food;
    private String message;

    public FoodResponse() {
    }

    public FoodResponse(Food food, String message) {
        this.food = food;
        this.message = message;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
