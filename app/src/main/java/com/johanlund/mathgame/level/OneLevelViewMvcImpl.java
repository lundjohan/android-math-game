package com.johanlund.mathgame.level;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager2.widget.ViewPager2;

import com.johanlund.mathgame.R;

public class OneLevelViewMvcImpl implements OneLevelViewMvc {
    View view;
    ViewPager2 viewPager;
    public OneLevelViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_one_level, container, false);
    }
    @Override
    public void bindPagerToView(QuestionAdapter qAdapter) {
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(qAdapter);
    }

    @Override
    public View getRootView() {
        return view;
    }
}
