package com.example.fastfood.ui.client.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fastfood.R;
import com.example.fastfood.ui.client.activities.LoginActivity;
import com.example.fastfood.ui.client.activities.RegisterActivity;
import com.example.fastfood.utils.SessionManager;

public class AccountFragment extends Fragment {
    private TextView tvChinhsua;
    private ImageView imgCustomer;
    private TextView tvName;
    private SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        tvChinhsua = view.findViewById(R.id.tvChinhsua);
        imgCustomer = view.findViewById(R.id.imgCustomer);
        tvName = view.findViewById(R.id.tvName);

        return view;
    }
}