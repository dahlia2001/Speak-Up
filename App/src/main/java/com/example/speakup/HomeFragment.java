package com.example.speakup;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    SpeakingFragment speakingFragment = new SpeakingFragment();
    StatFragment statFragment = new StatFragment();
    AppCompatButton games,speaking,videos,workout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        games = view.findViewById(R.id.favorites);
        speaking = view.findViewById(R.id.Quick);
        videos = view.findViewById(R.id.Language);
        workout = view.findViewById(R.id.startactivity);

        games.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), gamelayout.class);
            startActivity(intent);
        });

        speaking.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.homefragment, speakingFragment, "Speaking Fragment")
                    .addToBackStack(null)
                    .commit();
        });






        return view;

    }
}