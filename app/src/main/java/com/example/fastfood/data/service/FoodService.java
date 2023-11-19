package com.example.fastfood.data.service;

import com.example.fastfood.data.model.Food;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FoodService {
    @GET("food/all")
    Call<List<Food>> getAllFood();

    @POST("food/add")
    Call<Food> addFood(@Body Food food);

    @PUT("food/update/{id}")
    Call<Food> updateFood(@Path("id") Long id, @Body Food food);

    @DELETE("food/delete/{id}")
    Call<Food> deleteFood(@Path("id") Long id);
}
