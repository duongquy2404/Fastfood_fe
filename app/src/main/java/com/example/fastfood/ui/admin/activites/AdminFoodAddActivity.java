package com.example.fastfood.ui.admin.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastfood.R;

public class AdminFoodAddActivity extends AppCompatActivity {
    private ImageView ivBackAddToHome;
    private TextView tvAddImg;
    private EditText etNameFood, etPrice, etDescription, etAnh;
    private Spinner spinCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_food_add);
        init();
        clickEvent();
    }

    public void init(){
        ivBackAddToHome=(ImageView) findViewById(R.id.ivBackAddToHome);
        tvAddImg=(TextView) findViewById(R.id.tvAddImg);
        etNameFood=(EditText) findViewById(R.id.etNameFood);
        etPrice=(EditText) findViewById(R.id.etPrice);
        etDescription=(EditText) findViewById(R.id.etDescription);
        etAnh=(EditText) findViewById(R.id.etAnh);
        spinCategory=(Spinner) findViewById(R.id.spinCategory);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategory.setAdapter(adapter);
    }

    public void clickEvent(){
        ivBackAddToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminFoodAddActivity.this, AdminHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category=parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),category,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}