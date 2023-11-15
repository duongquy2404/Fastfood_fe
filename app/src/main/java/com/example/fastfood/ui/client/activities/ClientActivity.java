package com.example.fastfood.ui.client.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fastfood.R;
import com.example.fastfood.ui.client.adapters.ViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClientActivity extends AppCompatActivity {
    private BottomNavigationView bnvHome;
    private ViewPager2 vp_home;
    private ViewAdapter va;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        init();
        setViewPage();
        addMenuItem();
    }

    public void init(){
        bnvHome=findViewById(R.id.bnvHome);
        vp_home=findViewById(R.id.vp_home);
    }

    public void setViewPage(){
        va=new ViewAdapter(this);
        vp_home.setAdapter(va);
    }

    public void addMenuItem(){
        bnvHome.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.mHome){
                    vp_home.setCurrentItem(0);
                } else if (item.getItemId()==R.id.mCart) {
                    vp_home.setCurrentItem(1);
                } else if (item.getItemId()==R.id.mAccount) {
                    vp_home.setCurrentItem(2);
                }
                return true;
            }
        });
    }
}