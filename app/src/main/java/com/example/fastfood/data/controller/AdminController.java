package com.example.fastfood.data.controller;

import com.example.fastfood.data.api.ApiHelper;
import com.example.fastfood.data.model.dto.AdminLoginDTO;
import com.example.fastfood.data.model.dto.UserLoginDTO;
import com.example.fastfood.data.reponse.AdminResponse;
import com.example.fastfood.data.reponse.UserResponse;
import com.example.fastfood.data.service.AdminService;

import retrofit2.Call;
import retrofit2.Callback;

public class AdminController {
    private AdminService adminService;

    public AdminController(){
        adminService= ApiHelper.getClient().create(AdminService.class);
    }

    public void login(String username, String password, Callback<AdminResponse> callback) {
        AdminLoginDTO adminLoginDTO = new AdminLoginDTO(username, password);
        Call<AdminResponse> call = adminService.login(adminLoginDTO);
        call.enqueue(callback);
    }
}
