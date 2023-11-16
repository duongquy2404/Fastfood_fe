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
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class HomeFragment extends Fragment {
    private SearchView searchView;
    private ChipGroup chipGroup;
    private RecyclerView rvlistproduct;
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
        searchView = view.findViewById(R.id.searchView);
        chipGroup = view.findViewById(R.id.chipGroup);
        rvlistproduct = view.findViewById(R.id.rvlistproduct);

        rvlistproduct.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        for (String category : getCategories()) {
            Chip chip = new Chip(getContext());
            chip.setText(category);
            chipGroup.addView(chip);
        }

        // Set up ChipGroup listener
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                // Handle chip checked change
            }
        });
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private String[] getCategories() {
        return new String[]{"Category 1", "Category 2", "Category 3"};
    }
}