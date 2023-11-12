package com.example.fastfood.ui.admin.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.fastfood.R;
import com.example.fastfood.ui.client.activities.LoginActivity;

public class AdminLoginActivity extends AppCompatActivity {
    private ImageView ivBackLogin;
    private EditText etUsernameAdmin;
    private EditText etPasswordAdmin;
    private Button btnLoginAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
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
            }
        });
    }
}