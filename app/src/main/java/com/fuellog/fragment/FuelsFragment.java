package com.fuellog.fragment;


import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuellog.Constants;
import com.fuellog.R;
import com.fuellog.activity.AddFuelActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class FuelsFragment extends Fragment {

    public FuelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fuels, container, false);

        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddFuelActivity.class));
            }
        });

        return v;
    }





}
