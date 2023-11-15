package com.example.fastfood.ui.admin.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fastfood.R;

public class AdminHomeActivity extends AppCompatActivity {
    private Button btnAdd, btnTke;
    private RecyclerView rcvListFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        init();
        clickEvent();
    }

    public void init(){
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnTke = (Button) findViewById(R.id.btnTke);
    }

    public void clickEvent(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminHomeActivity.this, AdminFoodAddActivity.class);
                startActivity(intent);
            }
        });
        btnTke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminHomeActivity.this, AdminRevenueStatisticsActivity.class);
                startActivity(intent);
            }
        });
    }
}