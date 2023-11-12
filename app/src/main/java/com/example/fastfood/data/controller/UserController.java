package com.example.fastfood.data.controller;

import com.example.fastfood.data.api.ApiHelper;
import com.example.fastfood.data.model.dto.UserLoginDTO;
import com.example.fastfood.data.reponse.UserResponse;
import com.example.fastfood.data.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;

public class UserController {
    private UserService userService;

    public UserController() {
        userService = ApiHelper.getClient().create(UserService.class);
    }

    public void login(String username, String password, Callback<UserResponse> callback) {
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        Call<UserResponse> call = userService.login(userLoginDTO);
        call.enqueue(callback);
    }
}
