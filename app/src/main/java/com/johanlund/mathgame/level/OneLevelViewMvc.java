package com.johanlund.mathgame.level;

import com.johanlund.mathgame.common.ViewMvc;

public interface OneLevelViewMvc extends ViewMvc {
    void createViewPager2(QuestionAdapter qa);
    void bindLevelTitleToView(String str);
    void bindScoreToView(String str);
    void bindTimeToView(String str);


}
