package com.example.fastfood.data.service;

import com.example.fastfood.data.model.dto.AdminLoginDTO;
import com.example.fastfood.data.reponse.AdminResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AdminService {
    @POST("admin/login")
    Call<AdminResponse> login(@Body AdminLoginDTO body);
}
