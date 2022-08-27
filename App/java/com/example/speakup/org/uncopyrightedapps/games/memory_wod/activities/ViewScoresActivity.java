package com.example.speakup.org.uncopyrightedapps.games.memory_wod.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.speakup.R;
import com.example.speakup.org.uncopyrightedapps.games.memory_wod.adapters.ScoresListAdapter;
import com.example.speakup.org.uncopyrightedapps.games.memory_wod.adapters.ViewScoresPagerAdapter;
import com.example.speakup.org.uncopyrightedapps.games.memory_wod.data.GameDAO;
import com.example.speakup.org.uncopyrightedapps.games.memory_wod.engine.GameType;
import com.example.speakup.org.uncopyrightedapps.games.memory_wod.engine.Score;

import java.util.List;

public class ViewScoresActivity extends AbstractGameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);

        mView = findViewById(R.id.viewScores);

        ViewScoresPagerAdapter mSectionsPagerAdapter = new ViewScoresPagerAdapter(this, getSupportFragmentManager());

        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        goFullScreenWithNavigation();
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_GAME_TYPE = "game_type";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber, GameType gameType) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putSerializable(ARG_GAME_TYPE, gameType);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_view_scores, container, false);

            ListView scoreList = (ListView) rootView.findViewById(R.id.scoresList);

            GameType gameType = (GameType) getArguments().getSerializable(ARG_GAME_TYPE);
            if (gameType == null) {
                throw new IllegalArgumentException("Activity must receive a valid game type");
            }

            GameDAO mDao = GameDAO.getInstance(rootView.getContext());
            List<Score> items = mDao.getScores(gameType.getCode());

            ScoresListAdapter adapter = new ScoresListAdapter(rootView.getContext(), items);
            scoreList.setAdapter(adapter);

            return rootView;
        }
    }

}
