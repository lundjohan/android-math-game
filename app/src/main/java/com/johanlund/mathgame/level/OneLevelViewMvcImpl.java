package com.johanlund.mathgame.level;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager2.widget.ViewPager2;

import com.johanlund.mathgame.R;

public class OneLevelViewMvcImpl implements OneLevelViewMvc {
    View view;
    ViewPager2 viewPager;

    public OneLevelViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_one_level, container, false);
    }
    @Override
    public void createViewPager2(QuestionAdapter qAdapter) {
        viewPager = view.findViewById(R.id.pager);
        viewPager.requestTransform();
        viewPager.setAdapter(qAdapter);
    }

    @Override
    public void bindLevelTitleToView(String str) {
        TextView nameView = view.findViewById(R.id.levelTitle);
        nameView.setText(str);
    }

    @Override
    public void bindScoreToView(String str) {
        TextView scoreView = view.findViewById(R.id.score);
        scoreView.setText(str);
    }

    @Override
    public void removeQuestion(int nr) {

    }

    @Override
    public ViewPager2 getViewPager2() {
        return viewPager;
    }

    @Override
    public View getRootView() {
        return view;
    }
}
