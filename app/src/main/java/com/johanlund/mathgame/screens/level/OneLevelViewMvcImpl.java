package com.johanlund.mathgame.screens.level;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager2.widget.ViewPager2;

import com.johanlund.mathgame.R;

public class OneLevelViewMvcImpl implements OneLevelViewMvc {
    View view;
    ViewPager2 viewPager;
    TextView levelTitleView;
    TextView scoreView;
    TextView timeLeftView;

    public OneLevelViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_one_level, container, false);
        scoreView = view.findViewById(R.id.score);
        levelTitleView = view.findViewById(R.id.levelTitle);
        timeLeftView = view.findViewById(R.id.timeLeft);

    }

    @Override
    public void createViewPager2(QuestionAdapter qAdapter) {
        viewPager = view.findViewById(R.id.pager);

        //is this needed?
        viewPager.requestTransform();

        viewPager.setAdapter(qAdapter);
    }

    @Override
    public void bindLevelTitleToView(String str) {
        levelTitleView.setText(str);
    }

    @Override
    public void bindScoreToView(String str) {
        scoreView.setText(str);
    }

    @Override
    public void bindTimeToView(String str) {
        timeLeftView.setText(str);
    }

    @Override
    public View getRootView() {
        return view;
    }
}
