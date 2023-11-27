package com.example.fastfood.ui.client.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fastfood.R;
import com.example.fastfood.data.controller.FoodController;
import com.example.fastfood.data.model.Food;
import com.example.fastfood.ui.client.adapters.FoodAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private SearchView searchView;
    private ChipGroup chipGroup;
    private RecyclerView rvlisthome;
    private FoodAdapter foodAdapter;
    private FoodController foodController;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        foodController=new FoodController();
        searchView = view.findViewById(R.id.searchView);
        chipGroup = view.findViewById(R.id.chipGroup);
        rvlisthome = view.findViewById(R.id.rvlisthome);
        rvlisthome.setLayoutManager(new LinearLayoutManager(getActivity()));
        foodAdapter = new FoodAdapter(getActivity(), getFoodList());
        rvlisthome.setAdapter(foodAdapter);
        setChipGroup();
        return view;
    }

    public void setChipGroup(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submitted
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle search query text change
                return false;
            }
        });

        // Add Chips to ChipGroup
//        for (String category : getCategories()) {
//            Chip chip = new Chip(getContext());
//            chip.setText(category);
//            chipGroup.addView(chip);
//        }

        // Set up ChipGroup listener
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                // Handle chip checked change
            }
        });
    }

    private String[] getCategories() {
        return new String[]{"Category 1", "Category 2", "Category 3"};
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
                    foodAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });
        return foodList;
    }
}