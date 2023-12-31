package com.example.fastfood.data.controller;

import com.example.fastfood.data.api.ApiHelper;
import com.example.fastfood.data.model.User;
import com.example.fastfood.data.model.dto.UserLoginDTO;
import com.example.fastfood.data.model.dto.UserRegisterDTO;
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

    public void register(String name, String username, String password, Callback<UserResponse> callback){
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(name, username, password);
        Call<UserResponse> call = userService.register(userRegisterDTO);
        call.enqueue(callback);
    }

    public void getUserById(Long id, Callback<User> callback){
        Call<User> call = userService.getUserById(id);
        call.enqueue(callback);
    }

    public void updateUser(Long id, String name, String phone, String address, Callback<User> callback){
        User user=new User();
        user.setName(name);
        user.setPhone(phone);
        user.setAddress(address);
        Call<User> call = userService.updateUser(id,user);
        call.enqueue(callback);
    }

    public void updateAvatar(Long id, String avatar, Callback<User> callback){
        User user=new User();
        user.setAvatar(avatar);
        Call<User> call = userService.updateAvatar(id, user);
        call.enqueue(callback);
    }
}
