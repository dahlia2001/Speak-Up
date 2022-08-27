package com.example.speakup.org.uncopyrightedapps.games.memory_wod.adapters;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.speakup.org.uncopyrightedapps.games.memory_wod.activities.ViewScoresActivity;
import com.example.speakup.org.uncopyrightedapps.games.memory_wod.engine.GameType;

public class ViewScoresPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ViewScoresPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return ViewScoresActivity.PlaceholderFragment.newInstance(position + 1, getGameType(position));
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        GameType gameType = getGameType(position);
        return gameType != null ? mContext.getString(gameType.getResourceStringId()) : null;
    }

    GameType getGameType(int position) {
        switch (position) {
            case 0:
                return GameType.NO_BRAIN;
            case 1:
                return GameType.EASY;
            case 2:
                return GameType.MEDIUM;
            case 3:
                return GameType.HARD;
        }
        return null;
    }
}