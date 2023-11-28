package com.example.fastfood.data.service;

import com.example.fastfood.data.model.CartItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartItemService {
    @GET("/cart_item/{cartId}")
    Call<List<CartItem>> getCartItemsByCartId(@Path("cartId") Long cartId);

    @POST("/cart_item/create")
    Call<CartItem> createCartItem(@Body CartItem cartItem);

    @PUT("/cart_item/update/{id}")
    Call<CartItem> updateCartItem(@Path("id") Long id, @Body CartItem cartItem);

    @DELETE("/cart_item/delete/{id}")
    Call<CartItem> deleteCartItem(@Path("id") Long id);
}
