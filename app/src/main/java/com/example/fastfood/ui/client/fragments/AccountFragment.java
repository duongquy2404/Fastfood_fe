package com.example.fastfood.ui.client.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fastfood.R;
import com.example.fastfood.data.controller.UserController;
import com.example.fastfood.data.model.User;
import com.example.fastfood.data.reponse.UserResponse;
import com.example.fastfood.ui.client.activities.ClientActivity;
import com.example.fastfood.ui.client.activities.LoginActivity;
import com.example.fastfood.ui.client.activities.RegisterActivity;
import com.example.fastfood.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {
    private TextView tvChinhsua;
    private ImageView imgCustomer;
    private TextView tvName;
    private Button btnLogoutC;
    private UserController userController;
    private SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager=new SessionManager(requireContext());
        userController=new UserController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        tvChinhsua = view.findViewById(R.id.tvChinhsua);
        imgCustomer = view.findViewById(R.id.imgCustomer);
        tvName = view.findViewById(R.id.tvName);
        btnLogoutC = view.findViewById(R.id.btnLogoutC);
        infoUser();
        clickEvent();
        return view;
    }

    public void clickEvent(){
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
                    //UserResponse userResponse = response.body();
                    User user=response.body();
                    tvName.setText(user.getName());
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}