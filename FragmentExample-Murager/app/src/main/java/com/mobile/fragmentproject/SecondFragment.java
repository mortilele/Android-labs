package com.mobile.fragmentproject;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class SecondFragment extends Fragment {

    private TextView tvTitle;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(String text) {
        SecondFragment fragment = new SecondFragment();
        Bundle data = new Bundle();
        data.putString("title", text);
        fragment.setArguments(data);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = view.findViewById(R.id.tvTitle);
        String text = getArguments().getString("title");
        tvTitle.setText(text);
    }
}
