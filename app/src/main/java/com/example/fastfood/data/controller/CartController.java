package com.example.fastfood.data.controller;

import com.example.fastfood.data.api.ApiHelper;
import com.example.fastfood.data.model.Cart;
import com.example.fastfood.data.service.CartService;
import com.example.fastfood.data.service.FoodService;

import retrofit2.Call;
import retrofit2.Callback;

public class CartController {
    private CartService cartService;

    public CartController(){
        cartService = ApiHelper.getClient().create(CartService.class);
    }

    public void getCartByUserId(Long userId, Callback<Cart> callback){
        Call<Cart> call= cartService.getCartByUserId(userId);
        call.enqueue(callback);
    }
}
