package com.example.speakup;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SettingsFragment extends Fragment {

    AppCompatButton log_out,cus_details;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        log_out = view.findViewById(R.id.logout_button);
        cus_details = view.findViewById(R.id.contactinfo);

        log_out.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);
        });

        cus_details.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), Contact.class);
            startActivity(intent);
        });


        return view;

    }
}