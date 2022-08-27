package com.example.speakup;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.WindowManager;
import android.widget.Button;

import com.example.speakup.org.uncopyrightedapps.games.memory_wod.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class GameFragment extends AppCompatActivity{


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_login);
        startActivity(new Intent(GameFragment.this, com.example.speakup.org.uncopyrightedapps.games.memory_wod.activities.MainActivity.class));

    }

}