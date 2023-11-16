package com.example.fastfood.ui.client.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastfood.R;
import com.example.fastfood.data.controller.UserController;
import com.example.fastfood.data.model.User;
import com.example.fastfood.ui.client.fragments.AccountFragment;
import com.example.fastfood.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoActivity extends AppCompatActivity {
    private ImageButton ibBackAccount;
    private EditText edname, edPhone, edLocation;
    private Button btnSave;
    private UserController userController;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        userController=new UserController();
        sessionManager=new SessionManager(this);
        init();
        getInfo();
        clickEvent();
    }

    public void init(){
        ibBackAccount=(ImageButton) findViewById(R.id.ibBackAccount);
        edname=(EditText) findViewById(R.id.edName);
        edPhone=(EditText) findViewById(R.id.edPhone);
        edLocation=(EditText) findViewById(R.id.edLocation);
        btnSave=(Button) findViewById(R.id.btnSave);
    }

    public void clickEvent(){
        ibBackAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfo();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getInfo(){
        userController.getUserById(sessionManager.getUserId(), new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User user=response.body();
                    edname.setText(user.getName());
                    edPhone.setText(user.getPhone());
                    edLocation.setText(user.getAddress());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void setInfo(){
        if(TextUtils.isEmpty(edname.getText().toString())){
            Toast.makeText(getApplicationContext(),"Vui lòng nhập họ tên!", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(edPhone.getText().toString())){
            Toast.makeText(getApplicationContext(),"Vui lòng nhập số điện thoại!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edLocation.getText().toString())) {
            Toast.makeText(getApplicationContext(),"Vui lòng nhập địa chỉ!", Toast.LENGTH_SHORT).show();
        }else {
            String name=edname.getText().toString();
            String phone=edPhone.getText().toString();
            String address=edLocation.getText().toString();
            userController.updateUser(sessionManager.getUserId(), name, phone, address, new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Cập nhật thành công!!!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }
}