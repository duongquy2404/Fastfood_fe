package com.example.fastfood.ui.admin.activites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastfood.R;
import com.example.fastfood.data.controller.FoodController;
import com.example.fastfood.data.model.Category;
import com.example.fastfood.data.model.Food;
import com.example.fastfood.ui.client.activities.UserInfoActivity;
import com.example.fastfood.ui.client.fragments.AccountFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminFoodAddActivity extends AppCompatActivity {
    public static final String TAG = AdminFoodAddActivity.class.getName();
    private static final int MY_REQUEST_CODE = 11;
    private ImageView ivBackAddToHome, ivImgFood;
    private TextView tvAddImg;
    private EditText etNameFood, etPrice, etDescription;
    private Spinner spinCategory;
    private Button btnLuuAdd;
    private Uri mUri;
    private String imgUrl;
    private String category;
    private FoodController foodController;
    FirebaseStorage storage=FirebaseStorage.getInstance();

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG, "onActivityResult");
                    if(result.getResultCode()== Activity.RESULT_OK){
                        Intent data=result.getData();
                        if(data==null){
                            return;
                        }
                        Uri uri=data.getData();
                        mUri=uri;
                        try {
                            StorageReference storageRef = storage.getReference().child("food").child("food_" + System.currentTimeMillis() + ".png");
                            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            ivImgFood.setImageBitmap(bitmap);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                            byte[] data1 = baos.toByteArray();
                            UploadTask uploadTask = storageRef.putBytes(data1);
                            uploadTask.addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle unsuccessful uploads
                                }
                            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                                    // ...
                                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            imgUrl = uri.toString();
                                        }
                                    });
                                }
                            });
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_food_add);
        foodController=new FoodController();
        init();
        clickEvent();
    }

    public void init(){
        ivBackAddToHome=(ImageView) findViewById(R.id.ivBackAddToHome);
        ivImgFood=(ImageView) findViewById(R.id.ivImgFood);
        tvAddImg=(TextView) findViewById(R.id.tvAddImg);
        etNameFood=(EditText) findViewById(R.id.etNameFood);
        etPrice=(EditText) findViewById(R.id.etPrice);
        etDescription=(EditText) findViewById(R.id.etDescription);
        spinCategory=(Spinner) findViewById(R.id.spinCategory);
        btnLuuAdd=(Button) findViewById(R.id.btnLuuAdd);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategory.setAdapter(adapter);
    }

    public void clickEvent(){
        tvAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });
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
                category=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnLuuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });
    }

    public void addFood(){
        if(mUri==null){
            Toast.makeText(getApplicationContext(),"Vui lòng chọn ảnh!", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(etNameFood.getText().toString())){
            Toast.makeText(getApplicationContext(),"Vui lòng nhập tên cho món ăn!", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(etPrice.getText().toString())){
            Toast.makeText(getApplicationContext(),"Vui lòng nhập giá tiền!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etDescription.getText().toString())) {
            Toast.makeText(getApplicationContext(),"Vui lòng nhập thông tin món ăn!", Toast.LENGTH_SHORT).show();
        } else {
            String img=imgUrl;
            String name=etNameFood.getText().toString();
            Double price= Double.valueOf(etPrice.getText().toString());
            String description=etDescription.getText().toString();
            Long categoryId;
            if(category.equals("Pizza")){
                categoryId=1L;
            } else if (category.equals("Hamburger")) {
                categoryId=2L;
            }else {
                categoryId=3L;
            }

            Food food=new Food();
            food.setName(name);
            food.setPrice(price);
            food.setDescription(description);
            food.setImageUrl(img);
            Category category1=new Category();
            category1.setId(categoryId);
            category1.setName(category);
            food.setCategory(category1);

            foodController.addFood(food, new Callback<Food>() {
                @Override
                public void onResponse(Call<Food> call, Response<Food> response) {
                    Toast.makeText(AdminFoodAddActivity.this,"Thêm thành công!!!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Food> call, Throwable t) {
                    Toast.makeText(AdminFoodAddActivity.this,"Thêm thất bại!!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void onClickRequestPermission(){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            openGallery();
        }else {
            String[] permission={Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==MY_REQUEST_CODE){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(intent.createChooser(intent,"Select Picture"));
    }
}