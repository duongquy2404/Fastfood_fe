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
import com.example.fastfood.data.model.User;
import com.example.fastfood.data.model.dto.UserLoginDTO;
import com.example.fastfood.data.reponse.UserResponse;
import com.example.fastfood.ui.admin.activites.AdminLoginActivity;
import com.example.fastfood.ui.client.fragments.AccountFragment;
import com.example.fastfood.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsernameLoginC;
    private EditText etPasswordLoginC;
    private Button btnLoginC;
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
        etUsernameLoginC=(EditText) findViewById(R.id.etUsernameLoginC);
        etPasswordLoginC=(EditText) findViewById(R.id.etPasswordLoginC);
        btnLoginC=(Button) findViewById(R.id.btnLoginC);
        tvRegister=(TextView) findViewById(R.id.tvRegister);
        tvLoginAdmin=(TextView) findViewById(R.id.tvLoginAdmin);
    }

    public void clickEvent(){
        btnLoginC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
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

    public void login(){
        String username = etUsernameLoginC.getText().toString();
        String password = etPasswordLoginC.getText().toString();
        userController.login(username, password, new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    User user=new User();
                    user=userResponse.getUser();
                    sessionManager.setLogin(true, user.getId());
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