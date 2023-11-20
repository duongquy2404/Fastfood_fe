package com.example.fastfood.data.service;

import com.example.fastfood.data.model.Cart;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CartService {
    @GET("/cart/{userId}")
    Call<Cart> getCartByUserId(@Path("userId") Long userId);
}
