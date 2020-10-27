package com.johanlund.mathgame.welcomePage;

import android.widget.SeekBar;

import com.johanlund.mathgame.common.ViewMvc;

public interface WelcomeViewMvc extends ViewMvc {

    String retrieveLevelNrFromView();

    SeekBar getLevelChooser();

    void setDescriptionView(String description);

    void setDifficultyView(String difficulty);

    void setLevelNrView(String valueOf);

    interface Listener {

        void startPressed();
    }
}
