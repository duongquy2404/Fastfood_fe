package com.example.fastfood.ui.admin.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.example.fastfood.R;
import com.example.fastfood.data.controller.FoodController;
import com.example.fastfood.data.model.Food;
import com.example.fastfood.ui.admin.adapters.FoodManagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminHomeActivity extends AppCompatActivity {
    private TextView tvNameAdmin, tvPhoneAdmin, tvEmailAdmin;
    private ImageView ivLogoutAdmin;
    private Button btnAdd, btnTke;
    private FoodController foodController;
    private RecyclerView rcvListFood;
    private FoodManagerAdapter foodManagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        foodController=new FoodController();
        init();
        clickEvent();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void init() {
        tvNameAdmin=(TextView) findViewById(R.id.tvNameAdmin);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnTke = (Button) findViewById(R.id.btnTke);
        tvPhoneAdmin=(TextView) findViewById(R.id.tvPhoneAdmin);
        tvEmailAdmin=(TextView) findViewById(R.id.tvEmailAdmin);
        ivLogoutAdmin=(ImageView) findViewById(R.id.ivLogoutAdmin);

        rcvListFood = (RecyclerView) findViewById(R.id.rcvListFood);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvListFood.setLayoutManager(layoutManager);
        foodManagerAdapter = new FoodManagerAdapter(this, getFoodList());

        rcvListFood.setAdapter(foodManagerAdapter);
    }

    public void clickEvent(){
        ivLogoutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminHomeActivity.this, AdminLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminHomeActivity.this, AdminFoodAddActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnTke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminHomeActivity.this, AdminRevenueStatisticsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public List<Food> getFoodList(){
        List<Food> foodList=new ArrayList<>();
        foodController.getAllFood(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if(response.isSuccessful()){
                    List<Food> foods=new ArrayList<>();
                    foods.addAll(response.body());
                    foodList.addAll(foods);
                    foodManagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });
        return foodList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(foodManagerAdapter!=null){
            foodManagerAdapter.release();
        }
    }
}