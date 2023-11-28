package com.example.fastfood.ui.client.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.bumptech.glide.Glide;
import com.example.fastfood.R;
import com.example.fastfood.data.controller.UserController;
import com.example.fastfood.data.model.User;
import com.example.fastfood.ui.client.activities.LoginActivity;
import com.example.fastfood.ui.client.activities.UserInfoActivity;
import com.example.fastfood.utils.SessionManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {
    public static final String TAG = AccountFragment.class.getName();
    private static final int MY_REQUEST_CODE = 10;
    private TextView tvChinhsua;
    private ImageView imgCustomer;
    private TextView tvName, tvPhone, tvAddress;
    private Button btnLogoutC;
    private UserController userController;
    private SessionManager sessionManager;
    private Uri mUri;
    private ProgressDialog progressDialog;
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
                            showProgressDialog();
                            StorageReference storageRef = storage.getReference().child("user").child("user_" + System.currentTimeMillis() + ".png");
                            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                            imgCustomer.setImageBitmap(bitmap);
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
                                            String imageUrl = uri.toString();
                                            updateAvatar(imageUrl);
                                            dismissProgressDialog();
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager=new SessionManager(requireContext());
        userController=new UserController();
    }

    @Override
    public void onResume() {
        super.onResume();
        infoUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        tvChinhsua = view.findViewById(R.id.tvChinhsua);
        imgCustomer = view.findViewById(R.id.imgCustomer);
        tvName = view.findViewById(R.id.tvName);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvAddress =view.findViewById(R.id.tvAddress);
        btnLogoutC = view.findViewById(R.id.btnLogoutC);
        infoUser();
        clickEvent();
        return view;
    }

    public void clickEvent(){
        tvChinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
            }
        });
        imgCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });
        btnLogoutC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.setLogin(false, sessionManager.getUserId());
                closeFragment();
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void closeFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(this);
            fragmentTransaction.commit();
        }
    }

    public void infoUser(){
        userController.getUserById(sessionManager.getUserId(), new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user=response.body();
                    tvName.setText(user.getName());
                    if(user.getPhone()==null){
                        tvPhone.setText("Cập nhật thông tin!");
                    }else {
                        tvPhone.setText(user.getPhone());
                    }
                    if(user.getAddress()==null){
                        tvAddress.setText("Cập nhật thông tin!");
                    }else {
                        tvAddress.setText(user.getAddress());
                    }
                    if(user.getAvatar()!=null){
                        //Lấy ảnh từ firebase xuống
                        getAvatar(user.getAvatar());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void onClickRequestPermission(){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
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

    public void saveAvatar(){
        StorageReference storageRef = storage.getReference().child("user").child("user_" + System.currentTimeMillis() + ".png");
        // Get the data from an ImageView as bytes
        try {
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), mUri);
            imgCustomer.setImageBitmap(bitmap);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        imgCustomer.setDrawingCacheEnabled(true);
        imgCustomer.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imgCustomer.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = storageRef.putBytes(data);
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
                        String imageUrl = uri.toString();
                        updateAvatar(imageUrl);
                    }
                });
            }
        });
    }

    public void updateAvatar(String avatar){
        userController.updateAvatar(sessionManager.getUserId(), avatar, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void getAvatar(String imagePath){
        Glide.with(this)
                .load(imagePath)
                .into(imgCustomer);

    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("Đang tải ảnh lên...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}