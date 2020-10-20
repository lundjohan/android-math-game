package com.johanlund.mathgame.level;

import androidx.viewpager2.widget.ViewPager2;

import com.johanlund.mathgame.common.ViewMvc;

public interface OneLevelViewMvc extends ViewMvc {
    void bindPagerToView(QuestionAdapter qa);
}
