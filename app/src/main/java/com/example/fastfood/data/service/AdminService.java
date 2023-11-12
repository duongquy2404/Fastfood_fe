package com.example.fastfood.data.service;

import com.example.fastfood.data.model.dto.AdminLoginDTO;
import com.example.fastfood.data.reponse.AdminReponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AdminService {
    @POST("admin/login")
    Call<AdminReponse> login(@Body AdminLoginDTO body);
}
