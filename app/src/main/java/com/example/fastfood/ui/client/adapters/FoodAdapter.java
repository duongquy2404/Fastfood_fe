package com.example.fastfood.ui.client.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastfood.R;
import com.example.fastfood.data.model.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private List<Food> foodList;
    private LayoutInflater inflater;

    public FoodAdapter(Context context, List<Food> foodList){
        this.inflater=LayoutInflater.from(context);
        this.foodList=foodList;
    }

    public void release(){
        inflater=null;
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        Food food=foodList.get(position);
        Glide.with(inflater.getContext()).load(food.getImageUrl()).into(holder.ivImageFood);
        holder.tvNameFood.setText(food.getName());
        holder.tvPriceFood.setText(food.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return foodList != null ? foodList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImageFood;
        private TextView tvNameFood, tvPriceFood;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImageFood=itemView.findViewById(R.id.ivImageFood);
            tvNameFood=itemView.findViewById(R.id.tvNameFood);
            tvPriceFood=itemView.findViewById(R.id.tvPriceFood);
        }
    }
}
