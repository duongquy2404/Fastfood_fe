package com.example.fastfood.data.controller;

import com.example.fastfood.data.api.ApiHelper;
import com.example.fastfood.data.model.CartItem;
import com.example.fastfood.data.service.CartItemService;

import retrofit2.Call;
import retrofit2.Callback;

public class CartItemController {
    private CartItemService cartItemService;

    public CartItemController() {
        cartItemService = ApiHelper.getClient().create(CartItemService.class);
    }

    public void createCartItem(CartItem cartItem, Callback<CartItem> callback){
        Call<CartItem> call= cartItemService.createCartItem(cartItem);
        call.enqueue(callback);
    }
}
