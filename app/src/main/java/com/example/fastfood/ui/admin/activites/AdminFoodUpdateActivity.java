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

import com.bumptech.glide.Glide;
import com.example.fastfood.R;
import com.example.fastfood.data.controller.FoodController;
import com.example.fastfood.data.model.Category;
import com.example.fastfood.data.model.Food;
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

public class AdminFoodUpdateActivity extends AppCompatActivity {
    public static final String TAG = AdminFoodUpdateActivity.class.getName();
    private static final int MY_REQUEST_CODE = 12;
    private ImageView ivBackUpdateToHome, ivImgFoodUpdate;
    private TextView tvAddImgUpdate;
    private EditText etNameFoodUpdate, etPriceUpdate, etDescriptionUpdate;
    private Spinner spinCategoryUpdate;
    private Button btnLuuUpdate, btnDeleteUpdate;
    private Uri mUri;
    private String imgUrl;
    private String category;
    private Food food;
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
                            ivImgFoodUpdate.setImageBitmap(bitmap);
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
        setContentView(R.layout.activity_admin_food_update);
        foodController=new FoodController();
        Bundle bundle=getIntent().getExtras();
        if(bundle==null){
            return;
        }
        food= (Food) bundle.get("object_food");
        init();
        setInfo();
        clickEvent();
    }

    public void init(){
        ivBackUpdateToHome=(ImageView) findViewById(R.id.ivBackUpdateToHome);
        ivImgFoodUpdate=(ImageView) findViewById(R.id.ivImgFoodUpdate);
        tvAddImgUpdate=(TextView) findViewById(R.id.tvAddImgUpdate);
        etNameFoodUpdate=(EditText) findViewById(R.id.etNameFoodUpdate);
        etPriceUpdate=(EditText) findViewById(R.id.etPriceUpdate);
        etDescriptionUpdate=(EditText) findViewById(R.id.etDescriptionUpdate);
        spinCategoryUpdate=(Spinner) findViewById(R.id.spinCategoryUpdate);
        btnLuuUpdate=(Button) findViewById(R.id.btnLuuUpdate);
        btnDeleteUpdate=(Button) findViewById(R.id.btnDeleteUpdate);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategoryUpdate.setAdapter(adapter);
    }

    public void clickEvent(){
        tvAddImgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });
        ivBackUpdateToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminFoodUpdateActivity.this, AdminHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        spinCategoryUpdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnLuuUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFood();
            }
        });
        btnDeleteUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFood();
            }
        });
    }

    public void setInfo(){
        Glide.with(this)
                .load(food.getImageUrl())
                .into(ivImgFoodUpdate);
        etNameFoodUpdate.setText(food.getName());
        etPriceUpdate.setText(food.getPrice().toString());
        etDescriptionUpdate.setText(food.getDescription());
        if(food.getCategory().getId()==1L){
            spinCategoryUpdate.setSelection(0);
        } else if (food.getCategory().getId()==2L) {
            spinCategoryUpdate.setSelection(1);
        }else if(food.getCategory().getId()==3L){
            spinCategoryUpdate.setSelection(2);
        }
    }

    public void updateFood(){
        if(mUri==null){
            Toast.makeText(getApplicationContext(),"Vui lòng chọn ảnh!", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(etNameFoodUpdate.getText().toString())){
            Toast.makeText(getApplicationContext(),"Vui lòng nhập tên cho món ăn!", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(etPriceUpdate.getText().toString())){
            Toast.makeText(getApplicationContext(),"Vui lòng nhập giá tiền!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etDescriptionUpdate.getText().toString())) {
            Toast.makeText(getApplicationContext(),"Vui lòng nhập thông tin món ăn!", Toast.LENGTH_SHORT).show();
        } else {
            String img=imgUrl;
            String name=etNameFoodUpdate.getText().toString();
            Double price= Double.valueOf(etPriceUpdate.getText().toString());
            String description=etDescriptionUpdate.getText().toString();
            Long categoryId;
            if(category.equals("Pizza")){
                categoryId=1L;
            } else if (category.equals("Hamburger")) {
                categoryId=2L;
            }else {
                categoryId=3L;
            }

            Food food1=new Food();
            food1.setId(food.getId());
            food1.setName(name);
            food1.setPrice(price);
            food1.setDescription(description);
            food1.setImageUrl(img);
            Category category1=new Category();
            category1.setId(categoryId);
            category1.setName(category);
            food1.setCategory(category1);
            foodController.updateFood(food1.getId(), food1, new Callback<Food>() {
                @Override
                public void onResponse(Call<Food> call, Response<Food> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(AdminFoodUpdateActivity.this,"Lưu thành công!!!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Food> call, Throwable t) {
                    Toast.makeText(AdminFoodUpdateActivity.this,"Lưu thất bại!!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void deleteFood(){
        foodController.deleteFood(food.getId(), new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AdminFoodUpdateActivity.this,"Xóa thành công!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {
                Toast.makeText(AdminFoodUpdateActivity.this,"Xóa thành công!!!", Toast.LENGTH_SHORT).show();
            }
        });
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