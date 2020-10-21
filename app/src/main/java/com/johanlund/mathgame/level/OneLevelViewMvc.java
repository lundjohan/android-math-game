package com.johanlund.mathgame.level;

import androidx.viewpager2.widget.ViewPager2;

import com.johanlund.mathgame.common.ViewMvc;

public interface OneLevelViewMvc extends ViewMvc {
    void createViewPager2(QuestionAdapter qa);
    void bindLevelTitleToView(String str);
    void bindScoreToView(String str);

    void removeQuestion(int nr);

    ViewPager2 getViewPager2();
}
