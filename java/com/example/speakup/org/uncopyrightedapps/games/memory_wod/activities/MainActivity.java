package com.example.speakup.org.uncopyrightedapps.games.memory_wod.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.speakup.R;
import com.example.speakup.org.uncopyrightedapps.games.memory_wod.data.GameDAO;
import com.example.speakup.org.uncopyrightedapps.games.memory_wod.engine.GameEngine;
import com.example.speakup.org.uncopyrightedapps.games.memory_wod.engine.GameType;
import com.example.speakup.org.uncopyrightedapps.games.memory_wod.engine.Graphic;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  {

    protected View mView;
    private Graphic currentGraphics;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        mView = findViewById(R.id.mainLayout);
        Button mNoBrainButton = (Button) findViewById(R.id.noBrainButton);
        Button mEasyButton = (Button) findViewById(R.id.easyButton);
        Button mMediumButton = (Button) findViewById(R.id.mediumButton);
        Button mHardButton = (Button) findViewById(R.id.hardButton);
        Button mViewScoresButton = (Button) findViewById(R.id.viewScoresButton);

        mNoBrainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(1, 2, GameType.NO_BRAIN);
            }
        });

        mEasyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(2, 4, GameType.EASY);
            }
        });

        mMediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(4, 4, GameType.MEDIUM);
            }
        });

        mHardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(6, 4, GameType.HARD);
            }
        });

        mViewScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewScores();
            }
        });

        currentGraphics = dao().getLastGraphic();
        initGraphicsSpinner();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        }
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            String title = getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getBackStackEntryCount() - 1).getTag();
            super.onBackPressed();
        } else {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getResources().getString(R.string.Please_click_BACK_again_to_exit), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 1000);
        }
    }

    private void initGraphicsSpinner() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Spinner spinner = (Spinner) findViewById(R.id.graphicsSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.graphics, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(currentGraphics.getCode());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentGraphics = Graphic.getByCode(position);
                dao().saveLastGraphic(currentGraphics);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentGraphics = Graphic.ANIMALS;
            }
        });
    }

    private GameDAO dao() {
        return GameDAO.getInstance(mView.getContext());
    }

    private void startGame(int rowCount, int colCount, GameType gameType) {
        Intent intent = new Intent(this, PlayGameActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(PlayGameActivity.ARG_GAME_ENGINE, new GameEngine(rowCount, colCount, gameType));
        b.putSerializable(PlayGameActivity.ARG_GRAPHICS, currentGraphics);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void viewScores() {
        startActivity(new Intent(this, ViewScoresActivity.class));
    }

}
