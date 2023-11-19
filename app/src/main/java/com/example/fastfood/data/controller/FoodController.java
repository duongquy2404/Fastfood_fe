package com.example.fastfood.data.controller;

import com.example.fastfood.data.api.ApiHelper;
import com.example.fastfood.data.model.Food;
import com.example.fastfood.data.service.FoodService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;


public class FoodController {
    private FoodService foodService;

    public FoodController() {
        foodService = ApiHelper.getClient().create(FoodService.class);
    }

    public void getAllFood(Callback<List<Food>> callback){
        Call<List<Food>> call= foodService.getAllFood();
        call.enqueue(callback);
    }

    public void addFood(Food food, Callback<Food> callback){
        Food aFood=new Food();
        aFood.setName(food.getName());
        aFood.setPrice(food.getPrice());
        aFood.setDescription(food.getDescription());
        aFood.setCategory(food.getCategory());
        aFood.setImageUrl(food.getImageUrl());
        Call<Food> call= foodService.addFood(aFood);
        call.enqueue(callback);
    }
}
