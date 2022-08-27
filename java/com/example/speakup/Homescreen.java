package com.example.speakup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.speakup.org.uncopyrightedapps.games.memory_wod.activities.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationBarView;

public class Homescreen extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;
    BottomNavigationView bottomNavigationView;
    GameFragment gameFragment = new GameFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    SpeakingFragment speakingFragment = new SpeakingFragment();
    HomeFragment homeFragment = new HomeFragment();
    StatFragment statFragment = new StatFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homescreen);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;

                    case R.id.game:
                        
                        startActivity(new Intent(Homescreen.this, gamelayout.class));
                        return true;

                    case R.id.video:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, statFragment).commit();
                        return true;

                    case R.id.speaking:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, speakingFragment).commit();
                        return true;

                    case R.id.logout_final:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).commit();
                        return true;

                }
                return false;
            }


            private void unSelect(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(false);
                bottomNavigationView.getMenu().getItem(position).setCheckable(false);
            }

            private void select(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            public void backStackRemove() {
                for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                    getSupportFragmentManager().popBackStack();
                }
            }
        });
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
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        }
    }
}


