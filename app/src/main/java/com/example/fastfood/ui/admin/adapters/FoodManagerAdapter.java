package com.example.fastfood.ui.admin.adapters;

import android.annotation.SuppressLint;
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
import com.example.fastfood.ui.admin.activites.AdminFoodUpdateActivity;
import com.example.fastfood.utils.ConvertMoney;

import java.util.List;

public class FoodManagerAdapter extends RecyclerView.Adapter<FoodManagerAdapter.FoodManagerViewHolder> {
    private List<Food> foodList;
    private Context context;

    public FoodManagerAdapter(Context context, List<Food> foodList){
        this.context=context;
        this.foodList=foodList;
    }

    public void release(){
        context=null;
    }

    @NonNull
    @Override
    public FoodManagerAdapter.FoodManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_food, parent, false);

        return new FoodManagerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodManagerAdapter.FoodManagerViewHolder holder, int position) {
        Food food=foodList.get(position);
        Glide.with(context).load(food.getImageUrl()).into(holder.ivImageFood);
        holder.tvNameFood.setText(food.getName());
        holder.tvPriceFood.setText(ConvertMoney.formatCurrency(food.getPrice()));
        holder.llItemFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToUpdate(food);
            }
        });
    }

    private void onClickGoToUpdate(Food food){
        Intent intent=new Intent(context, AdminFoodUpdateActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("object_food", food);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return foodList != null ? foodList.size() : 0;
    }

    public class FoodManagerViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llItemFood;
        private ImageView ivImageFood;
        private TextView tvNameFood, tvPriceFood;
        public FoodManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            llItemFood=itemView.findViewById(R.id.llItemFood);
            ivImageFood=itemView.findViewById(R.id.ivImageFood);
            tvNameFood=itemView.findViewById(R.id.tvNameFood);
            tvPriceFood=itemView.findViewById(R.id.tvPriceFood);
        }
    }
}
