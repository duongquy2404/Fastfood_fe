package com.example.fastfood.data.service;

import com.example.fastfood.data.model.dto.UserLoginDTO;
import com.example.fastfood.data.model.dto.UserRegisterDTO;
import com.example.fastfood.data.reponse.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("users/login")
    Call<UserResponse> login(@Body UserLoginDTO body);

    @POST("users/register")
    Call<UserResponse> register(@Body UserRegisterDTO body);
}
