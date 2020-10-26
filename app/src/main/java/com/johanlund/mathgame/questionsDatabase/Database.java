package com.johanlund.mathgame.questionsDatabase;

import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.LevelInfo;

public interface Database {
    /**
     * @param nr >= 1
     * @return Level, or null if this level doesn't exist in database.
     * @throws is thrown if nr<1
     */
    Level getLevel(int nr) throws IllegalArgumentException;

    int getNrOfLevels();

    LevelInfo[] getLevelInfos();
}
