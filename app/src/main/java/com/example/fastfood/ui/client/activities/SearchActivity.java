package com.example.fastfood.ui.client.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.example.fastfood.R;
import com.example.fastfood.data.controller.FoodController;
import com.example.fastfood.data.model.Food;
import com.example.fastfood.ui.client.adapters.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private ImageView iv_back;
    private SearchView searchView1;
    private RecyclerView rcv_search;
    private SearchAdapter searchAdapter;
    private FoodController foodController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        foodController=new FoodController();
        init();
        clickEvent();
    }

    public void init(){
        iv_back=(ImageView) findViewById(R.id.iv_back);
        searchView1=(SearchView) findViewById(R.id.searchView1);

        rcv_search=(RecyclerView) findViewById(R.id.rcv_search);
        rcv_search.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter=new SearchAdapter(this);
        rcv_search.setAdapter(searchAdapter);
    }

    public void clickEvent(){
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchView1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchFood(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    searchFood(newText);
                } else {
                    searchAdapter.setData(new ArrayList<>());
                }
                return true;
            }
        });
    }

    public void searchFood(String newText){
        foodController.searchFoodsByName(newText, new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if(response.isSuccessful()){
                    searchAdapter.setData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });
    }
}