package com.example.fastfood.data.service;

import com.example.fastfood.data.model.User;
import com.example.fastfood.data.model.dto.UserLoginDTO;
import com.example.fastfood.data.model.dto.UserRegisterDTO;
import com.example.fastfood.data.reponse.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @POST("users/login")
    Call<UserResponse> login(@Body UserLoginDTO body);

    @POST("users/register")
    Call<UserResponse> register(@Body UserRegisterDTO body);

    @GET("users/{id}")
    Call<User> getUserById(@Path("id") Long userId);

    @PUT("users/update/{userId}")
    Call<User> updateUser(@Path("userId") Long userId, @Body User user);
}
