package com.johanlund.mathgame.screens.level;

import com.johanlund.mathgame.common.ViewMvc;

public interface LevelViewMvc extends ViewMvc {
    void createViewPager2(QuestionAdapter qa);

    void bindLevelTitleToView(String str);

    void bindScoreToView(String str);

    void bindTimeToView(String str);


}
