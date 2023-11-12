package com.example.fastfood.ui.client.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fastfood.R;
import com.example.fastfood.data.controller.UserController;
import com.example.fastfood.data.model.dto.UserLoginDTO;
import com.example.fastfood.data.reponse.UserResponse;
import com.example.fastfood.ui.admin.activites.AdminLoginActivity;
import com.example.fastfood.ui.client.fragments.AccountFragment;
import com.example.fastfood.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private TextView tvLoginAdmin;
    private UserController userController;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userController=new UserController();
        sessionManager=new SessionManager(this);
        init();
        clickEvent();
    }

    public void init(){
        etUsername=(EditText) findViewById(R.id.etUsername);
        etPassword=(EditText) findViewById(R.id.etPassword);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        tvRegister=(TextView) findViewById(R.id.tvRegister);
        tvLoginAdmin=(TextView) findViewById(R.id.tvLoginAdmin);
    }

    public void clickEvent(){
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        tvLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login(){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        userController.login(username, password, new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    sessionManager.setLogin(true, userResponse.getToken());
                    Intent intent = new Intent(LoginActivity.this, ClientActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Đăng nhập thất bại, xử lý lỗi
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}