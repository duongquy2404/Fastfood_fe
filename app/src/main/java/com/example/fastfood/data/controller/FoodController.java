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

    public void updateFood(Long id, Food food, Callback<Food> callback){
        Food uFood=new Food();
        uFood.setId(food.getId());
        uFood.setName(food.getName());
        uFood.setPrice(food.getPrice());
        uFood.setDescription(food.getDescription());
        uFood.setCategory(food.getCategory());
        uFood.setImageUrl(food.getImageUrl());
        Call<Food> call=foodService.updateFood(id, uFood);
        call.enqueue(callback);
    }

    public void deleteFood(Long id, Callback<Food> callback){
        Call<Food> call=foodService.deleteFood(id);
        call.enqueue(callback);
    }
}
