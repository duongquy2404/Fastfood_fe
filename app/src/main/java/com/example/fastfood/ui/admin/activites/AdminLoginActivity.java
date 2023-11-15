package com.example.fastfood.ui.admin.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.fastfood.R;
import com.example.fastfood.data.controller.AdminController;
import com.example.fastfood.data.model.User;
import com.example.fastfood.data.reponse.AdminResponse;
import com.example.fastfood.data.reponse.UserResponse;
import com.example.fastfood.ui.client.activities.ClientActivity;
import com.example.fastfood.ui.client.activities.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginActivity extends AppCompatActivity {
    private ImageView ivBackLogin;
    private EditText etUsernameAdmin;
    private EditText etPasswordAdmin;
    private Button btnLoginAdmin;
    private AdminController adminController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        adminController=new AdminController();
        init();
        clickEvent();
    }

    public void init(){
        ivBackLogin=(ImageView) findViewById(R.id.ivBackLogin);
        etUsernameAdmin=(EditText) findViewById(R.id.etUsernameAdmin);
        etPasswordAdmin=(EditText) findViewById(R.id.etPasswordAdmin);
        btnLoginAdmin=(Button) findViewById(R.id.btnLoginAdmin);
    }

    public void clickEvent(){
        ivBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminLoginActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        String username=etUsernameAdmin.getText().toString();
        String password=etPasswordAdmin.getText().toString();
        adminController.login(username, password, new Callback<AdminResponse>() {
            @Override
            public void onResponse(Call<AdminResponse> call, Response<AdminResponse> response) {
                if (response.isSuccessful()) {
                    AdminResponse adminResponse = response.body();
                    Intent intent = new Intent(AdminLoginActivity.this, AdminHomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AdminResponse> call, Throwable t) {

            }
        });
    }
}