package com.example.fastfood;

import static com.example.fastfood.utils.TimeContants.TIME_DELAY_START_APP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.example.fastfood.ui.client.activities.ClientActivity;
import com.example.fastfood.ui.client.activities.LoginActivity;
import com.example.fastfood.ui.client.adapters.ViewAdapter;
import com.example.fastfood.utils.SessionManager;
import com.example.fastfood.utils.TimeContants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sessionManager.isLoggedIn()) {
                    Intent clientIntent = new Intent(MainActivity.this, ClientActivity.class);
                    startActivity(clientIntent);
                } else {
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                }
                finish();
            }
        }, TIME_DELAY_START_APP);
    }
}