package com.example.fastfood.ui.client.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fastfood.R;
import com.example.fastfood.data.controller.CartController;
import com.example.fastfood.data.controller.CartItemController;
import com.example.fastfood.data.controller.UserController;
import com.example.fastfood.data.model.Cart;
import com.example.fastfood.data.model.CartItem;
import com.example.fastfood.data.model.Food;
import com.example.fastfood.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodInfoActivity extends AppCompatActivity {
    private ImageView ivBackFoodToHome, ivImageFoodInfo;
    private TextView tvNameFoodInfo, tvPriceFoodInfo, tvNumberFood, tvDescriptionFood;
    private ImageButton ibGiamFood, ibTangFood;
    private Button btnAddCart;
    private Food food;
    private SessionManager sessionManager;
    private CartController cartController;
    private CartItemController cartItemController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);
        sessionManager=new SessionManager(this);
        cartController=new CartController();
        cartItemController=new CartItemController();
        Bundle bundle=getIntent().getExtras();
        if(bundle==null){
            return;
        }
        food= (Food) bundle.get("info_food");
        init();
        setInfoFood();
        clickEvent();
    }

    public void init(){
        ivBackFoodToHome=(ImageView) findViewById(R.id.ivBackFoodToHome);
        ivImageFoodInfo=(ImageView) findViewById(R.id.ivImageFoodInfo);
        tvNameFoodInfo=(TextView) findViewById(R.id.tvNameFoodInfo);
        tvPriceFoodInfo=(TextView) findViewById(R.id.tvPriceFoodInfo);
        tvNumberFood=(TextView) findViewById(R.id.tvNumberFood);
        tvDescriptionFood=(TextView) findViewById(R.id.tvDescriptionFood);
        ibGiamFood=(ImageButton) findViewById(R.id.ibGiamFood);
        ibTangFood=(ImageButton) findViewById(R.id.ibTangFood);
        btnAddCart=(Button) findViewById(R.id.btnAddCart);
    }

    public void setInfoFood(){
        Glide.with(this)
                .load(food.getImageUrl())
                .into(ivImageFoodInfo);
        tvNameFoodInfo.setText(food.getName());
        tvPriceFoodInfo.setText(food.getPrice().toString());
        tvDescriptionFood.setText(food.getDescription());
    }

    public void clickEvent(){
        ivBackFoodToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ibGiamFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ibGiamFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddCartItem();
            }
        });
    }

    public void onClickAddCartItem(){
        CartItem cartItem=new CartItem();
        cartItem.setFood(food);
        Cart cart=new Cart();
        cart.setId(sessionManager.getUserId());
        cartItem.setCart(cart);
        int n=Integer.parseInt(String.valueOf(tvNumberFood.getText()));
        cartItem.setQuantity(n);
        cartItemController.createCartItem(cartItem, new Callback<CartItem>() {
            @Override
            public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                if(response.isSuccessful()){
                    Toast.makeText(FoodInfoActivity.this,"Đã thêm vào giỏ hàng!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartItem> call, Throwable t) {

            }
        });
    }

    private void getCart(){
        cartController.getCartByUserId(sessionManager.getUserId(), new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()){
                    //Cart cart1=response.body();
                    //cart=response.body();
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });
    }
}