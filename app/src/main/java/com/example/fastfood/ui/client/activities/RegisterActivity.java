package com.example.fastfood.ui.client.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fastfood.R;
import com.example.fastfood.data.controller.UserController;
import com.example.fastfood.data.model.User;
import com.example.fastfood.data.reponse.UserResponse;
import com.example.fastfood.ui.admin.activites.AdminLoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText etNameRegisterC, etUsernameRegisterC, etPasswordRegisterC, etRePasswordRegisterC;
    private ImageView ivBackLogin;
    private Button btnRegisterC;
    private UserController userController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userController=new UserController();
        init();
        clickEvent();
    }

    public void init(){
        etNameRegisterC = (EditText) findViewById(R.id.etNameRegisterC);
        etUsernameRegisterC = (EditText) findViewById(R.id.etUsernameRegisterC);
        etPasswordRegisterC = (EditText) findViewById(R.id.etPasswordRegisterC);
        etRePasswordRegisterC = (EditText) findViewById(R.id.etRePasswordRegisterC);
        ivBackLogin = (ImageView) findViewById(R.id.ivBackLogin);
        btnRegisterC = (Button) findViewById(R.id.btnRegisterC);
    }

    public void clickEvent(){
        ivBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnRegisterC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register(){
        String name=etNameRegisterC.getText().toString();
        String username=etUsernameRegisterC.getText().toString();
        String password=etPasswordRegisterC.getText().toString();
        String repassword=etRePasswordRegisterC.getText().toString();
        if(password.matches(repassword)){
            userController.register(name, username, password, new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Đăng kí thất bại, xử lý lỗi
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                }
            });
        }else {
            Toast.makeText(RegisterActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
        }
    }
}