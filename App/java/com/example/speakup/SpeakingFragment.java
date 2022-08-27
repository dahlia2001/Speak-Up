package com.example.speakup;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SpeakingFragment extends Fragment {

    AppCompatButton game1,game2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_speaking, container, false);

        game1 = view.findViewById(R.id.Quick_match);
        game2 = view.findViewById(R.id.language_game);

        game1.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), com.example.speakup.speakingactivity.class);
            startActivity(intent);
        });

        game2.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), languageactivity.class);
            startActivity(intent);
        });

        return view;

    }
}