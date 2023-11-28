package com.example.fastfood.ui.admin.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fastfood.R;

public class AdminRevenueStatisticsActivity extends AppCompatActivity {
    private ImageView ivBackRevenueToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_revenue_statistics);
        init();
        clickEvent();
    }

    public void init(){
        ivBackRevenueToHome=(ImageView) findViewById(R.id.ivBackRevenueToHome);
    }

    public void clickEvent(){
        ivBackRevenueToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminRevenueStatisticsActivity.this, AdminHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}