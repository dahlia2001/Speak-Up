package com.example.speakup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class IntroductoryActivity extends AppCompatActivity {


    private static int SPLASH_SCREEN = 5000;


    Animation top, bottom;
    TextView appName, subAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_introductory);

        top = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        appName = findViewById(R.id.appName);
        subAppName = findViewById(R.id.subAppName);

        appName.setAnimation(top);
        subAppName.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroductoryActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);


    }
}