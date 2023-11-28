package com.example.fastfood.ui.client.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastfood.R;
import com.example.fastfood.data.model.Food;
import com.example.fastfood.ui.client.activities.FoodInfoActivity;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<Food> foodList;
    private Context context;
    public SearchAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<Food> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Food food=foodList.get(position);
        Glide.with(context).load(food.getImageUrl()).into(holder.ivImageSearch);
        holder.tvNameSearch.setText(food.getName());
        holder.llItemSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToFoodInfo(food);
            }
        });
    }

    private void onClickGoToFoodInfo(Food food) {
        Intent intent=new Intent(context, FoodInfoActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("info_food", food);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return foodList != null ? foodList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llItemSearch;
        private ImageView ivImageSearch;
        private TextView tvNameSearch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llItemSearch=itemView.findViewById(R.id.llItemSearch);
            ivImageSearch=itemView.findViewById(R.id.ivImageSearch);
            tvNameSearch=itemView.findViewById(R.id.tvNameSearch);
        }
    }
}
