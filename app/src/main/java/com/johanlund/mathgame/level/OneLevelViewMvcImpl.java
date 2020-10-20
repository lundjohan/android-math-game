package com.johanlund.mathgame.level;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager2.widget.ViewPager2;

import com.johanlund.mathgame.R;

public class OneLevelViewMvcImpl implements OneLevelViewMvc {
    View view;
    
    public OneLevelViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_one_level, container, false);
    }
    @Override
    public void bindPagerToView(QuestionAdapter qAdapter) {
        ViewPager2 viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(qAdapter);
    }

    @Override
    public void bindLevelTitleToView(String str) {
        TextView nameView = view.findViewById(R.id.levelTitle);
        nameView.setText(str);
    }

    @Override
    public View getRootView() {
        return view;
    }
}
