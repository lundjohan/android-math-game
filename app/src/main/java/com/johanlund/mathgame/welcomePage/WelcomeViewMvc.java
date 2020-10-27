package com.johanlund.mathgame.welcomePage;

import com.johanlund.mathgame.common.LevelInfo;
import com.johanlund.mathgame.common.ViewMvc;

public interface WelcomeViewMvc extends ViewMvc {

    String retrieveLevelNrFromView();

    interface Listener {

        void startPressed();


        //Amount of levels as the user sees it.
        //Levels start at 1
        int getFirstLevelNr();

        //Levels last value is the same as levels.size
        int getLastLevelNr();

        /**
         *
         * @param levelNr >= 1
         * @return
         */
        LevelInfo getInfoForLevelWithNr(int levelNr);
    }
}
