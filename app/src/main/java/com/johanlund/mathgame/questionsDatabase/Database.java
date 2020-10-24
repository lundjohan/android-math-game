package com.johanlund.mathgame.questionsDatabase;

import com.johanlund.mathgame.common.Level;

public interface Database {
    /**
     * @param nr >= 1
     * @exception is thrown if nr<1
     * @return Level, or null if this level doesn't exist in database.
     */
    Level getLevel(int nr) throws IllegalArgumentException;

    int getNrOfLevels();
}
